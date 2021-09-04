package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prot.apitool.openapi.spec.model.v30.AnyOrExpressionObject;

import java.io.IOException;

public class AnyOrExpressionObjectDeserializer extends JsonDeserializer<AnyOrExpressionObject> {
    @Override
    public AnyOrExpressionObject deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        JsonNode tree = ctx.readTree(p);
        if (tree == null) {
            return null;
        } else {
            Object val = ((ObjectMapper)p.getCodec()).treeToValue(tree, Object.class);
            if (val == null) {
                return null;
            } else {
                if (AnyOrExpressionObject.isExpression(val)) {
                    return AnyOrExpressionObject.expression((String) val);
                } else {
                    return AnyOrExpressionObject.any(val);
                }
            }
        }
    }
}
