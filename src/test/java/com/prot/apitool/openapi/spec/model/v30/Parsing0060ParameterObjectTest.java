package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Parsing0060ParameterObjectTest extends AbstractParsingModelTest {
    private ParameterObject expectedResult;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void init() {
        expectedResult = new ParameterObject();
        expectedResult.setName("token");
        expectedResult.setIn(ParameterObject.InLocation.header);
        expectedResult.setRequired(true);
        expectedResult.setStyle("simple");
        expectedResult.setDescription("token to be passed as a header");
        SchemaObject schema = new SchemaObject();
        schema.put("type", "array");
        schema.put("items", new LinkedHashMap<String, Object>());
        ((Map<String, Object>)schema.get("items")).put("type", "integer");
        ((Map<String, Object>)schema.get("items")).put("format", "int64");
        expectedResult.setSchema(EitherModelOrReferenceObject.modelObject(schema));
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0060-parameter-object.yaml";
        verifyParseYaml(expectedResult, resourceName, ParameterObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0060-parameter-object.json";
        verifyParseJson(expectedResult, resourceName, ParameterObject.class);
    }

}
