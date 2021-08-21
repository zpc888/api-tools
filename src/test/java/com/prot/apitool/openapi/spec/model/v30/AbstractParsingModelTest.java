package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractParsingModelTest {
    protected <T> void verifyParseYaml(T expected, String resourceName, Class<T> modelType) throws Exception {
        final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.findAndRegisterModules();
        T actual = yamlMapper.readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName)
                , modelType);
        assertEquals(expected, actual);

        final String yaml = yamlMapper.writeValueAsString(actual);
        System.out.println(yaml);
        T roundtrip = yamlMapper.readValue(yaml, modelType);
        assertEquals(expected, roundtrip);
    }

    protected <T> void verifyParseJson(T expected, String resourceName, Class<T> modelType) throws Exception {
        final ObjectMapper jsonMapper = new ObjectMapper();
        T actual = jsonMapper.readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName)
                , modelType);
        assertEquals(expected, actual);

        final String json = jsonMapper.writeValueAsString(actual);
        System.out.println(json);
        T roundtrip = jsonMapper.readValue(json, modelType);
        assertEquals(expected, roundtrip);
    }
}
