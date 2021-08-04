package com.prot.apitool.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AdminController {
    @GetMapping("/admin")
    public Mono<String> admin() {
        return Mono.just("admin get => ok");
    }
    @PostMapping("/api")
    public Mono<String> api() {
        return Mono.just("api post => ok");
    }
}
