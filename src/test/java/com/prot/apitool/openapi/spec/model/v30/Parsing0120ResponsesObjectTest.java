package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class Parsing0120ResponsesObjectTest extends AbstractParsingModelTest {
    private ResponsesObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new ResponsesObject();
        ResponseObject okResponse = new ResponseObject();
        okResponse.setDescription("a pet to be returned");
        expectedResult.put("200", EitherModelOrReferenceObject.modelObject(okResponse));
        MediaTypeObject okMediaType = new MediaTypeObject();
        okMediaType.setSchema(EitherModelOrReferenceObject.referenceObject(new ReferenceObject("#/components/schemas/Pet")));
        LinkedHashMap<String, MediaTypeObject> okContent = new LinkedHashMap<>();
        okContent.put("application/json", okMediaType);
        okResponse.setContent(okContent);

        ResponseObject defaultResponse = new ResponseObject();
        defaultResponse.setDescription("Unexpected error");
        expectedResult.setDefault(EitherModelOrReferenceObject.modelObject(defaultResponse));
        MediaTypeObject defaultMediaType = new MediaTypeObject();
        defaultMediaType.setSchema(EitherModelOrReferenceObject.referenceObject(new ReferenceObject("#/components/schemas/ErrorModel")));
        LinkedHashMap<String, MediaTypeObject> defaultContent = new LinkedHashMap<>();
        defaultContent.put("application/json", defaultMediaType);
        defaultResponse.setContent(defaultContent);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0120-responses-object.yaml";
        verifyParseYaml(expectedResult, resourceName, ResponsesObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0120-responses-object.json";
        verifyParseJson(expectedResult, resourceName, ResponsesObject.class);
    }
}
