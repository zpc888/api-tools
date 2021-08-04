package com.prot.apitool.mock.model.def;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public class ObjectNodeJPAConverter implements AttributeConverter<ObjectNode, String> {
    private final ObjectMapper om;

    public ObjectNodeJPAConverter() {
        this.om = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(ObjectNode attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return om.writeValueAsString(attribute);
        } catch (JsonProcessingException ex) {
            log.error("fail to convert ObjectNode [" + attribute + "] to string value", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectNode convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        }
        try {
            return om.readValue(dbData, ObjectNode.class);
        } catch (JsonProcessingException ex) {
            log.error("fail to convert string [" + dbData + "] to ObjectNode value", ex);
            throw new RuntimeException(ex);
        }
    }
}
