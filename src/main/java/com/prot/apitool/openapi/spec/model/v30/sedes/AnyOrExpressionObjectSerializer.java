package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.prot.apitool.openapi.spec.model.v30.AnyOrExpressionObject;

import java.io.IOException;

public class AnyOrExpressionObjectSerializer extends JsonSerializer<AnyOrExpressionObject> {
    @Override
    public void serialize(AnyOrExpressionObject obj, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (obj == null) {
            return;
        } else {
            Object toBeWrite = obj.isExpression() ? obj.getExpression() : obj.getAny();
            SeDesUtils.serialize(toBeWrite, gen, serializers);
        }
    }
}
