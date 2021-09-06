package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class Parsing0100HeaderObjectTest extends AbstractParsingModelTest {
    private HeaderObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new HeaderObject();
        expectedResult.setDescription("The number of allowed requests in the current period");
        SchemaObject schemaObject = new SchemaObject();
        EitherModelOrReferenceObject<SchemaObject> schema = EitherModelOrReferenceObject.modelObject(schemaObject);
        expectedResult.setSchema(schema);
        schemaObject.put("type", "integer");
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0100-header-object.yaml";
        verifyParseYaml(expectedResult, resourceName, HeaderObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0100-header-object.json";
        verifyParseJson(expectedResult, resourceName, HeaderObject.class);
    }
}
