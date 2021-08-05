package com.prot.apitool.mock.service;

import com.prot.apitool.mock.model.def.MockApi;
import com.prot.apitool.mock.model.def.SharedApiResponse;
import com.prot.apitool.mock.repo.MockApiRepository;
import com.prot.apitool.mock.repo.MockSharedResponseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class MockApiService {
    private final MockApiRepository mockApiRepository;
    private final MockSharedResponseRepository mockSharedResponseRepository;

    public List<MockApi> findAllApisInLightweight() {
        return mockApiRepository.getAllLightApis().stream().map(idUrlMethod -> {
            MockApi ret = new MockApi();
            ret.setContentType(null);
            ret.setStatusCode(0);
            ret.setId( ((BigInteger)idUrlMethod[0]).longValue() );
            ret.setUrlPattern( (String) idUrlMethod[1] );
            ret.setHttpMethod( (String) idUrlMethod[2] );
            return ret;
        }).collect(Collectors.toList());
    }

    public Optional<MockApi> findMockApiById(Long id) {
        return mockApiRepository.findById(id);
    }

    public MockApi saveMockApi(MockApi mockApi) {
        mockApiRepository.save(mockApi);
        return mockApi;
    }

    public List<SharedApiResponse> findAllSharedApiResponses() {
        return mockSharedResponseRepository.findAll();
    }

    public Optional<SharedApiResponse> findSharedApiResponseById(Long id) {
        return mockSharedResponseRepository.findById(id);
    }

    public SharedApiResponse saveSharedApiResponse(SharedApiResponse sharedApiResp) {
        mockSharedResponseRepository.save(sharedApiResp);
        return sharedApiResp;
    }
}
