package com.prot.apitool.mock.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MockHandler {
    public Mono<ServerResponse> get(ServerRequest request) {
        final String path = request.uri().getPath();
        log.debug("request parameter: {}", request.queryParams());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request.uri().getPath() + " get => ok"));
    }
}
