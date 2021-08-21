package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Parsing0020ContactObjectTest extends AbstractParsingModelTest {
    private ContactObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new ContactObject();
        expectedResult.setName("API Support");
        expectedResult.setUrl("http://www.example.com/support");
        expectedResult.setEmail("support@example.com");
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-sepc-model-samples/0020-contact-object.yaml";
        verifyParseYaml(expectedResult, resourceName, ContactObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-sepc-model-samples/0020-contact-object.json";
        verifyParseJson(expectedResult, resourceName, ContactObject.class);
    }

}