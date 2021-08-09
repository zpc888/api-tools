package com.prot.apitool.mock.dto;

import java.util.List;

class MatchedUriParsedResult implements UriParsedResult {
    private final FlexMap requestPathMap;
    private final FlexMap requestParamMap;

    public MatchedUriParsedResult(FlexMap requestPathMap, FlexMap requestParamMap) {
        this.requestPathMap = requestPathMap;
        this.requestParamMap = requestParamMap;
    }

    @Override
    public boolean matched() {
        return true;
    }

    private String getSingleValue(FlexMap map, String key) {
        return map != null ? map.value(key) : null;
    }

    private List<String> getMultiValue(FlexMap map, String key) {
        return map != null ? map.values(key) : null;
    }

    @Override
    public String path(String var) {
        return getSingleValue(requestPathMap, var);
    }

    @Override
    public List<String> paths(String var) {
        return getMultiValue(requestPathMap, var);
    }

    @Override
    public String param(String var) {
        return getSingleValue(requestParamMap, var);
    }

    @Override
    public List<String> params(String var) {
        return getMultiValue(requestParamMap, var);
    }
}
