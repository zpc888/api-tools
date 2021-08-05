package com.prot.apitool.mock.repo;

import com.prot.apitool.mock.model.def.MockApi;
import com.prot.apitool.mock.model.def.MockApiBranch;
import com.prot.apitool.mock.model.def.SharedApiResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {H2RepositoryConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
class H2RepositoryTest {
    @Resource
    private MockSharedResponseRepository sharedApiResponseRepo;
    @Resource
    private MockApiRepository apiRepo;

    @AfterEach
    void cleanup() {
        apiRepo.deleteAll();
        sharedApiResponseRepo.deleteAll();
    }

    @Test
    public void apiRepo_with_defaultResponse() {
        List<MockApi> mockApis = apiRepo.findAll();
        assertEquals(0, mockApis.size());

        MockApi api = new MockApi();
        api.setUrlPattern("/api/hi");
        api.setHttpMethod(HttpMethod.GET.name());
        api.setContentTemplate("hello world!");
        apiRepo.save(api);
        mockApis = apiRepo.findAll();
        assertEquals(1, mockApis.size());
        assertEquals(api, mockApis.get(0));

        verifyLightApis(api);
    }

    private void verifyLightApis(MockApi... apis) {
        List<Object[]> lightApis = apiRepo.getAllLightApis();
        assertEquals(apis.length, lightApis.size());
        for (int i=0; i<apis.length; i++) {
            MockApi api = apis[i];
            assertEquals(3, lightApis.get(i).length);
            assertEquals(api.getId(), ((BigInteger)lightApis.get(i)[0]).longValue());
            assertEquals(api.getUrlPattern(), lightApis.get(i)[1]);
            assertEquals(api.getHttpMethod(), lightApis.get(i)[2]);
        }
    }

    @Test
    public void apiRepo_with_sharedResponse() {
        List<MockApi> mockApis = apiRepo.findAll();
        assertEquals(0, mockApis.size());

        MockApi api = new MockApi();
        api.setUrlPattern("/api/echo");
        api.setHttpMethod(HttpMethod.GET.name());
        SharedApiResponse sharedApiResp = new SharedApiResponse();
        sharedApiResp.setContentTemplate("123");
        sharedApiResponseRepo.save(sharedApiResp);
        assertEquals(1, sharedApiResp.getId().intValue());
        api.setSharedApiResponse(sharedApiResp);
        apiRepo.save(api);

        mockApis = apiRepo.findAll();
        assertEquals(1, mockApis.size());
        assertEquals(api, mockApis.get(0));

        verifyLightApis(api);
    }

    @Test
    public void apiRepo_with_branchedResponse() {
        List<MockApi> mockApis = apiRepo.findAll();
        assertEquals(0, mockApis.size());

        MockApi api = new MockApi();
        api.setUrlPattern("/api/echo/{action}");
        api.setHttpMethod(HttpMethod.GET.name());
        MockApiBranch branch1 = new MockApiBranch();
        branch1.setConditionExpr("req.path.var['action'] == 'case1'");
        branch1.setContentTemplate("case 1 output");
        MockApiBranch branch2 = new MockApiBranch();
        branch2.setConditionExpr("req.path.var['action'] != 'case1'");
        branch2.setContentTemplate("case 2 output");
        api.setConditionalBranches(Arrays.asList(branch1, branch2));
        apiRepo.save(api);

        mockApis = apiRepo.findAll();
        assertEquals(1, mockApis.size());
        assertEquals(api, mockApis.get(0));

        verifyLightApis(api);
    }

    @Test
    public void sharedApiResponse_CRUD() {
        List<SharedApiResponse> sharedApiResponses = sharedApiResponseRepo.findAll();
        assertEquals(0, sharedApiResponses.size());

        SharedApiResponse sharedApiResp1 = new SharedApiResponse();
        sharedApiResp1.setContentTemplate("123");
        sharedApiResponseRepo.save(sharedApiResp1);
        assertTrue(sharedApiResp1.getId().intValue() > 0);

        sharedApiResponses = sharedApiResponseRepo.findAll();
        assertEquals(1, sharedApiResponses.size());
        assertEquals(sharedApiResp1, sharedApiResponses.get(0));

        sharedApiResponseRepo.deleteById(sharedApiResp1.getId());
        sharedApiResponses = sharedApiResponseRepo.findAll();
        assertEquals(0, sharedApiResponses.size());
    }

}