package com.prot.apitool.mock.dto;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.PathContainer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UriParser {
    private static final UriParsedResult NO_MATCH = new UriParsedResult() {
        @Override
        public boolean matched() {
            return false;
        }

        @Override
        public String path(String var) {
            return null;
        }

        @Override
        public List<String> paths(String var) {
            return Collections.emptyList();
        }

        @Override
        public String param(String var) {
            return null;
        }

        @Override
        public List<String> params(String var) {
            return Collections.emptyList();
        }
    };

    final String requestUriPattern;
    final String requestPathPart;
    final Map<String, String> requestParamParts;

    // caching
    final PathPattern pathPattern;
    final Map<String, PathPattern> paramPatterns;

    public UriParser(String requestUriPattern) {
        this.requestUriPattern = requestUriPattern;
        int pos = requestUriPattern.indexOf('?');
        if (pos == -1) {
            requestPathPart = requestUriPattern;
            requestParamParts = Collections.emptyMap();
        } else {
            requestPathPart = requestUriPattern.substring(0, pos);
            final String queryParamStrings = requestUriPattern.substring(pos + 1);
            if (StringUtils.isEmpty(queryParamStrings)) {
                requestParamParts = Collections.emptyMap();
            } else {
                String[] keyValues = StringUtils.split(queryParamStrings, '&');
                requestParamParts = new HashMap<>(keyValues.length);
                for (String kv: keyValues) {
                    int eqPos = kv.indexOf('=');
                    if (eqPos == -1) {
                        requestParamParts.put(kv, "");
                    } else {
                        requestParamParts.put(kv.substring(0, eqPos).trim(), kv.substring(eqPos + 1).trim());
                    }
                }
            }
        }
        PathPatternParser pathPatternParser = PathPatternParser.defaultInstance;
        pathPattern = pathPatternParser.parse(requestPathPart);
        if (requestParamParts.isEmpty()) {
            paramPatterns = Collections.emptyMap();
        } else {
            paramPatterns = new HashMap<>(requestParamParts.size());
            for (String k: requestParamParts.keySet()) {
                String val = requestParamParts.get(k);
                paramPatterns.put(k, pathPatternParser.parse(val));
            }
        }
    }

    public UriParsedResult parseUri(ServerRequest request) {
        final String requestPath = request.path();
        PathContainer pathContainer = PathContainer.parsePath(requestPath);
        if (!pathPattern.matches(pathContainer)) {
            return NO_MATCH;
        }
        PathPattern.PathMatchInfo matchInfo = pathPattern.matchAndExtract(pathContainer);
        FlexMap pathParts = buildPathParts(matchInfo.getUriVariables(), matchInfo.getMatrixVariables());
        FlexMap paramParts = buildParamParts( request.queryParams() );
        return new MatchedUriParsedResult(pathParts, paramParts);
    }

    private FlexMap buildParamParts(MultiValueMap<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return null;
        }
        FlexMap ret = new FlexMap();
        doCollectMultiValMap(ret, queryParams);
        for (String key: requestParamParts.keySet()) {
            PathPattern queryPattern = paramPatterns.get(key);
            if (queryPattern != null) {
                List<String> values = queryParams.get(key);
                if (!CollectionUtils.isEmpty(values)) {
                    for (String val: values) {
                        PathPattern.PathMatchInfo matchInfo = queryPattern.matchAndExtract(PathContainer.parsePath(val));
                        collectSingle(ret, matchInfo.getUriVariables());
                        collectMulti(ret, matchInfo.getMatrixVariables());
                    }
                }
            }
        }
        return ret;
    }

    private FlexMap buildPathParts(Map<String, String> uriVars, Map<String, MultiValueMap<String, String>> matrixVars) {
        if (MapUtils.isEmpty(uriVars) && matrixVars.isEmpty()) {
            return null;
        }
        FlexMap ret = new FlexMap();
        collectSingle(ret, uriVars);
        collectMulti(ret, matrixVars);
        return ret;
    }

    private void collectSingle(FlexMap holder, Map<String, String> map) {
        if (MapUtils.isEmpty(map)) {
            return;
        }
        for (String k: map.keySet()) {
            holder.add(k, map.get(k));
        }
    }

    private void collectMulti(FlexMap holder, Map<String, MultiValueMap<String, String>> map) {
        if (MapUtils.isEmpty(map)) {
            return;
        }
        for (String k: map.keySet()) {
            MultiValueMap<String, String> multiVals = map.get(k);
            doCollectMultiValMap(holder, multiVals);
        }
    }

    private void doCollectMultiValMap(FlexMap holder, MultiValueMap<String, String> multiVals) {
        for (String k2: multiVals.keySet()) {
            List<String> vals = multiVals.get(k2);
            if (CollectionUtils.isEmpty(vals)) {
                holder.add(k2, null);
            } else {
                for (String val: vals) {
                    holder.add(k2, val);
                }
            }
        }
    }
}
