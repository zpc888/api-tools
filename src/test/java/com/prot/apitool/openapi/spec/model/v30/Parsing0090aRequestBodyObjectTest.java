package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class Parsing0090aRequestBodyObjectTest extends AbstractParsingModelTest {
    private RequestBodyObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new RequestBodyObject();
        expectedResult.setDescription("user to add to the system");
        expectedResult.setRequired(Boolean.TRUE);
        LinkedHashMap<String, MediaTypeObject> map = new LinkedHashMap<>();
        expectedResult.setContent(map);
        MediaTypeObject txtMediaType = new MediaTypeObject();
        map.put("text/plain", txtMediaType);

        SchemaObject schemaObject = new SchemaObject();
        EitherModelOrReferenceObject<SchemaObject> txtSchema = EitherModelOrReferenceObject.modelObject(schemaObject);
        txtMediaType.setSchema(txtSchema);
        schemaObject.put("type", "array");
        LinkedHashMap<String, String> items = new LinkedHashMap<>();
        items.put("type", "string");
        schemaObject.put("items", items);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0090a-request-body-object.yaml";
        verifyParseYaml(expectedResult, resourceName, RequestBodyObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0090a-request-body-object.json";
        verifyParseJson(expectedResult, resourceName, RequestBodyObject.class);
    }
}
