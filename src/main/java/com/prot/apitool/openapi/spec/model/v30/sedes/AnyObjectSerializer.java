package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.prot.apitool.openapi.spec.model.v30.AnyObject;

import java.io.IOException;

public class AnyObjectSerializer extends JsonSerializer<AnyObject> {
    @Override
    public void serialize(AnyObject obj, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (obj == null) {
            return;
        } else {
            SeDesUtils.serialize(obj.getValue(), gen, serializers);
        }
    }
}
