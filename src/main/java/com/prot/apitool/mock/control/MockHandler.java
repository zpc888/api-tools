package com.prot.apitool.mock.control;

import com.prot.apitool.mock.dto.UriParsedResult;
import com.prot.apitool.mock.model.def.MockApi;
import com.prot.apitool.mock.service.MockApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.pattern.PathPattern;
import reactor.core.publisher.Mono;

import java.util.*;

@AllArgsConstructor
@Slf4j
@Component
public class MockHandler {
    private final MockApiService mockApiService;

    public Mono<ServerResponse> get(ServerRequest request) {
        final String path = request.uri().getPath();
        final String httpMethod = request.methodName();
        log.debug("request parameter: {}", request.queryParams());

        final PathContainer pathContainer = PathContainer.parsePath(path);
        List<MockApi> allApis = mockApiService.findAllApisInLightweight();
        MockApi matchedApi = null;
        PathPattern.PathMatchInfo pathMatchInfo = null;
        for (MockApi mockApi: allApis) {
            Pair<Boolean, PathPattern.PathMatchInfo> matchAndExtract = mockApi.matchesRequestMethodAndPath(httpMethod, pathContainer);
            UriParsedResult result = mockApi.matchRequest(request);
            if (matchAndExtract.getLeft().booleanValue()) {
                matchedApi = mockApi;
                pathMatchInfo = matchAndExtract.getRight();
                break;
            }
        }

        if (matchedApi == null) {
            return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(Collections.singletonMap("errorReason"
                            , String.format("No matched mock-api for request '%s':'%s'", httpMethod, path))));
        }
        log.debug("Found mock api #{}-{} for request {}:{}", matchedApi.getId(), matchedApi.getUrlPattern(),
                matchedApi.getHttpMethod(), path);
        Optional<MockApi> detailedApi = mockApiService.findMockApiById(matchedApi.getId());
        if (detailedApi.isEmpty()) {
            log.warn("Mock api #{} was just deleted", matchedApi.getId());
            return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(Collections.singletonMap("errorReason"
                            , String.format("No matched mock-api for request '%s':'%s'", httpMethod, path))));
        }
        matchedApi = detailedApi.get();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(matchedApi.getContentTemplate()));
    }
}
