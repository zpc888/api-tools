package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class Parsing0070MediaObjectTest extends AbstractParsingModelTest {
    private LinkedHashMap<String, MediaTypeObject> expectedResult;
    private TypeReference<LinkedHashMap<String, MediaTypeObject>> typeRef;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void init() {
        typeRef = new TypeReference<LinkedHashMap<String, MediaTypeObject>>() {};
        expectedResult = new LinkedHashMap<>();
        MediaTypeObject mediaTypeObject = new MediaTypeObject();
        expectedResult.put("application/json", mediaTypeObject);
        mediaTypeObject.setSchema(EitherModelOrReferenceObject.referenceObject(new ReferenceObject("#/components/schemas/Pet")));
        Map<String, EitherModelOrReferenceObject<ExampleObject>> examples = new LinkedHashMap<>();
        mediaTypeObject.setExamples(examples);

        ExampleObject cat = new ExampleObject();
        cat.setSummary("An example of a cat");
        cat.setValue(buildExampleValue("name", "Fluffy", "petType", "Cat", "color", "White", "gender", "male",
                "breed", "Persian"));
        examples.put("cat", EitherModelOrReferenceObject.modelObject(cat));

        ExampleObject dog = new ExampleObject();
        dog.setSummary("An example of a dog with a cat's name");
        dog.setValue(buildExampleValue("name", "Puma", "petType", "Dog", "color", "Black", "gender", "Female",
                "breed", "Mixed"));
        examples.put("dog", EitherModelOrReferenceObject.modelObject(dog));

        examples.put("frog", EitherModelOrReferenceObject.referenceObject(new ReferenceObject("#/components/examples/frog-example")));
    }

    private AnyObject buildExampleValue(Object... keyValues) {
        LinkedHashMap<String, Object> ret = new LinkedHashMap<>();
        for (int i = 0; i < keyValues.length; i+=2) {
            ret.put((String)keyValues[i], keyValues[i+1]);
        }
        return new AnyObject(ret);
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0070-media-object.yaml";
        verifyParseYaml(expectedResult, resourceName, typeRef);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0070-media-object.json";
        verifyParseJson(expectedResult, resourceName, typeRef);
    }

}
