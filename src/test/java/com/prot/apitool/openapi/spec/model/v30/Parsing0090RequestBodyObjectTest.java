package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class Parsing0090RequestBodyObjectTest extends AbstractParsingModelTest {
    private RequestBodyObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new RequestBodyObject();
        expectedResult.setDescription("user to add to the system");
        LinkedHashMap<String, MediaTypeObject> map = new LinkedHashMap<>();
        expectedResult.setContent(map);
        MediaTypeObject jsonMediaType = new MediaTypeObject();
        map.put("application/json", jsonMediaType);
        MediaTypeObject xmlMediaType = new MediaTypeObject();
        map.put("application/xml", xmlMediaType);
        MediaTypeObject txtMediaType = new MediaTypeObject();
        map.put("text/plain", txtMediaType);
        MediaTypeObject wildcharMediaType = new MediaTypeObject();
        map.put("*/*", wildcharMediaType);

        EitherModelOrReferenceObject<SchemaObject> jsonSchema = EitherModelOrReferenceObject.referenceObject(new ReferenceObject("#/components/schemas/User"));
        jsonMediaType.setSchema(jsonSchema);
        Map<String, EitherModelOrReferenceObject<ExampleObject>> jsonExamples = new LinkedHashMap<>();
        ExampleObject jsonExample = new ExampleObject();
        jsonExample.setSummary("User example");
        jsonExample.setExternalValue("http://foo.bar/examples/user-example.json");
        jsonExamples.put("user", EitherModelOrReferenceObject.modelObject(jsonExample));
        jsonMediaType.setExamples(jsonExamples);

        EitherModelOrReferenceObject<SchemaObject> xmlSchema = EitherModelOrReferenceObject.referenceObject(new ReferenceObject("#/components/schemas/User"));
        xmlMediaType.setSchema(xmlSchema);
        Map<String, EitherModelOrReferenceObject<ExampleObject>> xmlExamples = new LinkedHashMap<>();
        ExampleObject xmlExample = new ExampleObject();
        xmlExample.setSummary("User example in XML");
        xmlExample.setExternalValue("http://foo.bar/examples/user-example.xml");
        xmlExamples.put("user", EitherModelOrReferenceObject.modelObject(xmlExample));
        xmlMediaType.setExamples(xmlExamples);

        Map<String, EitherModelOrReferenceObject<ExampleObject>> txtExamples = new LinkedHashMap<>();
        ExampleObject txtExample = new ExampleObject();
        txtExample.setSummary("User example in Plain text");
        txtExample.setExternalValue("http://foo.bar/examples/user-example.txt");
        txtExamples.put("user", EitherModelOrReferenceObject.modelObject(txtExample));
        txtMediaType.setExamples(txtExamples);

        Map<String, EitherModelOrReferenceObject<ExampleObject>> wildcharExamples = new LinkedHashMap<>();
        ExampleObject wildcharExample = new ExampleObject();
        wildcharExample.setSummary("User example in other format");
        wildcharExample.setExternalValue("http://foo.bar/examples/user-example.whatever");
        wildcharExamples.put("user", EitherModelOrReferenceObject.modelObject(wildcharExample));
        wildcharMediaType.setExamples(wildcharExamples);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0090-request-body-object.yaml";
        verifyParseYaml(expectedResult, resourceName, RequestBodyObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0090-request-body-object.json";
        verifyParseJson(expectedResult, resourceName, RequestBodyObject.class);
    }
}
