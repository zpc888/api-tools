package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Parsing0080ExampleObjectTest extends AbstractParsingModelTest {
    private ExampleObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new ExampleObject();
        expectedResult.setSummary("This is an example in XML");
        expectedResult.setExternalValue("http://example.org/examples/address-example.xml");
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0080-example-object.yaml";
        verifyParseYaml(expectedResult, resourceName, ExampleObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0080-example-object.json";
        verifyParseJson(expectedResult, resourceName, ExampleObject.class);
    }
}
