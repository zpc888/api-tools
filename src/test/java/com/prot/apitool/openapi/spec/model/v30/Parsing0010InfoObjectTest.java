package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Parsing0010InfoObjectTest extends AbstractParsingModelTest {
    private InfoObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new InfoObject();
        expectedResult.setTitle("Sample Pet Store App");
        expectedResult.setDescription("This is a sample server for a pet store.");
        expectedResult.setTermsOfService("http://example.com/terms/");
        expectedResult.setVersion("1.0.1");

        ContactObject contact = new ContactObject();
        contact.setName("API Support");
        contact.setUrl("http://www.example.com/support");
        contact.setEmail("support@example.com");
        expectedResult.setContact(contact);

        LicenseObject license = new LicenseObject();
        license.setName("Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");
        expectedResult.setLicense(license);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0010-info-object.yaml";
        verifyParseYaml(expectedResult, resourceName, InfoObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0010-info-object.json";
        verifyParseJson(expectedResult, resourceName, InfoObject.class);
    }

}