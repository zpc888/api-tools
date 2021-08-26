package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Parsing0000SpecFragmentServers01Test extends AbstractParsingModelTest {
    private OpenAPISpec expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new OpenAPISpec();
        List<ServerObject> servers = new ArrayList<ServerObject>();
        expectedResult.setServers(servers);
        ServerObject devServer = new ServerObject();
        devServer.setUrl("https://development.gigantic-server.com/v1");
        devServer.setDescription("Development server");
        servers.add(devServer);
        ServerObject stagingServer = new ServerObject();
        stagingServer.setUrl("https://staging.gigantic-server.com/v1");
        stagingServer.setDescription("Staging server");
        servers.add(stagingServer);
        ServerObject prodServer = new ServerObject();
        prodServer.setUrl("https://api.gigantic-server.com/v1");
        prodServer.setDescription("Production server");
        servers.add(prodServer);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0000-spec-fragment-servers-01.yaml";
        verifyParseYaml(expectedResult, resourceName, OpenAPISpec.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0000-spec-fragment-servers-01.json";
        verifyParseJson(expectedResult, resourceName, OpenAPISpec.class);
    }

}