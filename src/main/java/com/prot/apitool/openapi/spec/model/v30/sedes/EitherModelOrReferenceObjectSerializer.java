package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prot.apitool.openapi.spec.model.v30.EitherModelOrReferenceObject;

import java.io.IOException;

public class EitherModelOrReferenceObjectSerializer extends JsonSerializer<EitherModelOrReferenceObject> {
    @Override
    public void serialize(EitherModelOrReferenceObject either, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (either == null) {
            return;
        } else if (either.isReferenceObject()) {
            ObjectNode tree = ((ObjectMapper)gen.getCodec()).valueToTree(either.getReferenceObject());
            gen.writeTree(tree);
        } else if (either.isModelObject()) {
            ObjectNode tree = ((ObjectMapper)gen.getCodec()).valueToTree(either.getModelObject());
            gen.writeTree(tree);
        } else {
            return;
        }
    }
}
