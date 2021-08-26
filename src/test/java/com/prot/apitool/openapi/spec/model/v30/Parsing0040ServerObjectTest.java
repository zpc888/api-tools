package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Parsing0040ServerObjectTest extends AbstractParsingModelTest {
    private ServerObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new ServerObject();
        expectedResult.setDescription("Development server");
        expectedResult.setUrl("https://development.gigantic-server.com/v1");
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0040-server-object.yaml";
        verifyParseYaml(expectedResult, resourceName, ServerObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0040-server-object.json";
        verifyParseJson(expectedResult, resourceName, ServerObject.class);
    }

}