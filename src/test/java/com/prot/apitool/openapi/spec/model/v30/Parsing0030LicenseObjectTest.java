package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Parsing0030LicenseObjectTest extends AbstractParsingModelTest {
    private LicenseObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new LicenseObject();
        expectedResult.setName("Apache 2.0");
        expectedResult.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0030-license-object.yaml";
        verifyParseYaml(expectedResult, resourceName, LicenseObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0030-license-object.json";
        verifyParseJson(expectedResult, resourceName, LicenseObject.class);
    }

}