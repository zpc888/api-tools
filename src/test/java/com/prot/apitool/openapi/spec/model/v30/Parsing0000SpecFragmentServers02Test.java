package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Parsing0000SpecFragmentServers02Test extends AbstractParsingModelTest {
    private OpenAPISpec expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new OpenAPISpec();
        List<ServerObject> servers = new ArrayList<ServerObject>();
        expectedResult.setServers(servers);
        ServerObject prodServer = new ServerObject();
        prodServer.setUrl("https://{username}.gigantic-server.com:{port}/{basePath}");
        prodServer.setDescription("The production API server");
        servers.add(prodServer);
        prodServer.setVariables(new HashMap<String, ServerVariableObject>(8));
        ServerVariableObject usernameVar = new ServerVariableObject();
        usernameVar.setDefaultValue("demo");
        usernameVar.setDescription("this value is assigned by the service provider, in this example `gigantic-server.com`");
        prodServer.getVariables().put("username", usernameVar);
        ServerVariableObject portVar = new ServerVariableObject();
        portVar.setDefaultValue("8443");
        portVar.setEnumValues(new ArrayList<>());
        portVar.getEnumValues().add("8443");
        portVar.getEnumValues().add("443");
        prodServer.getVariables().put("port", portVar);
        ServerVariableObject basePathVar = new ServerVariableObject();
        basePathVar.setDefaultValue("v2");
        prodServer.getVariables().put("basePath", basePathVar);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0000-spec-fragment-servers-02.yaml";
        verifyParseYaml(expectedResult, resourceName, OpenAPISpec.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0000-spec-fragment-servers-02.json";
        verifyParseJson(expectedResult, resourceName, OpenAPISpec.class);
    }
}