package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.prot.apitool.openapi.spec.model.v30.EitherModelOrReferenceObject;
import com.prot.apitool.openapi.spec.model.v30.ReferenceObject;
import com.prot.apitool.openapi.spec.model.v30.SpecModel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class EitherModelOrReferenceObjectDeserializer extends JsonDeserializer<EitherModelOrReferenceObject> {
    @Override
    public EitherModelOrReferenceObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode tree = ctxt.readTree(p);
        if (tree == null) {
            return null;
        } else if (tree.has("$ref")) {
            ReferenceObject referenceObject = ((ObjectMapper)p.getCodec()).treeToValue(tree, ReferenceObject.class);
            return EitherModelOrReferenceObject.referenceObject(referenceObject);
        } else {        // Specific model object
            Class parentClass = p.getCurrentValue().getClass();
            String propName = p.getCurrentName();
            try {
                Field f = parentClass.getDeclaredField(propName);
                Type fieldType = f.getGenericType();
                if (!(fieldType instanceof ParameterizedType)) {
                    throw new IOException("it is not generic type for field: [" + propName + "] for class: [" + parentClass + "]");
                }
                Type actualType = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
                if (! SpecModel.class.isAssignableFrom((Class)actualType)) {
                    throw new IOException("it is not SpecModel type for field: [" + propName + "] for class: [" + parentClass + "]");
                }
                SpecModel model = ((ObjectMapper)p.getCodec()).treeToValue(tree, (Class<SpecModel>) actualType);
                return EitherModelOrReferenceObject.modelObject(model);
            } catch (NoSuchFieldException e) {
                throw new IOException("no such field: [" + propName + "] for class: [" + parentClass + "]");
            }
        }
    }
}
