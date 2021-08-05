package com.prot.apitool.mock.control;

import com.prot.apitool.mock.model.def.MockApi;
import com.prot.apitool.mock.model.def.SharedApiResponse;
import com.prot.apitool.mock.service.MockApiService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/mock-api-admin")
public class MockApiController {
    private final MockApiService mockApiService;

    @GetMapping("/shared-responses")
    public Flux<SharedApiResponse> findAllSharedApiResponses() {
        return Flux.fromIterable(mockApiService.findAllSharedApiResponses());
    }

    @GetMapping("/shared-responses/{resId}")
    public ResponseEntity<Mono<SharedApiResponse>> findSharedResponseById(@PathVariable("resId") Long id) {
        Optional<SharedApiResponse> optionalRes = mockApiService.findSharedApiResponseById(id);
        if (optionalRes.isPresent()) {
            return ResponseEntity.ok(Mono.just(optionalRes.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/shared-responses")
    public ResponseEntity<Mono<SharedApiResponse>> createSharedResponse(@RequestBody SharedApiResponse sharedApiResponse) {
        if (sharedApiResponse.getId() != null) {
            log.error("To create a shared response, but it has already an ID: {}", sharedApiResponse.getId());
            return ResponseEntity.badRequest().build();
        }
        SharedApiResponse sharedResp = mockApiService.saveSharedApiResponse(sharedApiResponse);
        return ResponseEntity.ok(Mono.just(sharedResp));
    }

    @PutMapping("/shared-responses")
    public ResponseEntity<Mono<SharedApiResponse>> updateSharedResponse(@RequestBody SharedApiResponse sharedApiResponse) {
        if (sharedApiResponse.getId() == null) {
            log.error("To update a shared response, but it has no ID");
            return ResponseEntity.badRequest().build();
        }
        SharedApiResponse sharedResp = mockApiService.saveSharedApiResponse(sharedApiResponse);
        return ResponseEntity.ok(Mono.just(sharedResp));
    }

    @GetMapping("/apis")
    public Flux<MockApi> findAllApisInLightweight() {
        return Flux.fromIterable(mockApiService.findAllApisInLightweight());
    }

    @GetMapping("/apis/{apiID}")
    public ResponseEntity<Mono<MockApi>> findApiById(@PathVariable("apiID") Long id) {
        Optional<MockApi> optionalApi = mockApiService.findMockApiById(id);
        if (optionalApi.isPresent()) {
            return ResponseEntity.ok(Mono.just(optionalApi.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/apis")
    public ResponseEntity<Mono<MockApi>> createMockApi(@RequestBody MockApi mockApi) {
        if (mockApi.getId() != null) {
            log.error("To create a mock api, but it has already an ID: {}", mockApi.getId());
            return ResponseEntity.badRequest().build();
        }
        MockApi api = mockApiService.saveMockApi(mockApi);
        return ResponseEntity.ok(Mono.just(api));
    }

    @PutMapping("/apis")
    public ResponseEntity<Mono<MockApi>> updateMockApi(@RequestBody MockApi mockApi) {
        if (mockApi.getId() == null) {
            log.error("To update a mock api, but it has no ID");
            return ResponseEntity.badRequest().build();
        }
        MockApi api = mockApiService.saveMockApi(mockApi);
        return ResponseEntity.ok(Mono.just(api));
    }
}
