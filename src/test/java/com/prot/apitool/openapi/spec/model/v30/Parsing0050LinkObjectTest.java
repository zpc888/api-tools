package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class Parsing0050LinkObjectTest extends AbstractParsingModelTest {
    private LinkObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new LinkObject();
        expectedResult.setOperationId("getUserAddress");
        expectedResult.setParameters(new HashMap<String, AnyOrExpressionObject>());
        expectedResult.getParameters().put("userId", AnyOrExpressionObject.expression("$request.path.id"));
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0050-link-object.yaml";
        verifyParseYaml(expectedResult, resourceName, LinkObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0050-link-object.json";
        verifyParseJson(expectedResult, resourceName, LinkObject.class);
    }

}
