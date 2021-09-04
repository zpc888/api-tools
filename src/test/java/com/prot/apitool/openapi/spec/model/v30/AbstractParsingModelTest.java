package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.core.type.TypeReference;
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

        final String yaml = yamlMapper.writeValueAsString(actual);
        System.out.printf("actual yaml:%n%s%n", yaml);
        T roundtrip = yamlMapper.readValue(yaml, modelType);
        System.out.printf("roundtrip yaml:%n%s%n", yamlMapper.writeValueAsString(roundtrip));

        assertEquals(expected, actual);
        assertEquals(expected, roundtrip);
    }

    protected <T> void verifyParseJson(T expected, String resourceName, Class<T> modelType) throws Exception {
        final ObjectMapper jsonMapper = new ObjectMapper();
        T actual = jsonMapper.readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName)
                , modelType);

        final String json = jsonMapper.writeValueAsString(actual);
        System.out.printf("actual json:%n%s%n", json);
        T roundtrip = jsonMapper.readValue(json, modelType);
        System.out.printf("roundtrip json:%n%s%n", jsonMapper.writeValueAsString(roundtrip));

        assertEquals(expected, actual);
        assertEquals(expected, roundtrip);
    }

    protected <T> void verifyParseYaml(T expected, String resourceName, TypeReference<T> modelType) throws Exception {
        final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.findAndRegisterModules();
        T actual = yamlMapper.readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName)
                , modelType);

        final String yaml = yamlMapper.writeValueAsString(actual);
        System.out.printf("actual yaml:%n%s%n", yaml);
        T roundtrip = yamlMapper.readValue(yaml, modelType);
        System.out.printf("roundtrip yaml:%n%s%n", yamlMapper.writeValueAsString(roundtrip));

        assertEquals(expected, actual);
        assertEquals(expected, roundtrip);
    }

    protected <T> void verifyParseJson(T expected, String resourceName, TypeReference<T> modelType) throws Exception {
        final ObjectMapper jsonMapper = new ObjectMapper();
        T actual = jsonMapper.readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName)
                , modelType);

        final String json = jsonMapper.writeValueAsString(actual);
        System.out.printf("actual json:%n%s%n", json);
        T roundtrip = jsonMapper.readValue(json, modelType);
        System.out.printf("roundtrip json:%n%s%n", jsonMapper.writeValueAsString(roundtrip));

        assertEquals(expected, actual);
        assertEquals(expected, roundtrip);
    }

}
