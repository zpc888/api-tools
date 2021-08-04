package com.prot.apitool.mock.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class MockRouter {
    static final String INTERNAL_API_PREFIX = "/{internalApiPrefix:(?!(?:admin|api)).*}/**";
    @Bean
    public RouterFunction<ServerResponse> route(MockHandler handler) {
        return RouterFunctions.route(
                GET(INTERNAL_API_PREFIX).and(accept(MediaType.APPLICATION_JSON))
                , handler::get);
    }

}
