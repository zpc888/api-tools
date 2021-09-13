package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.prot.apitool.openapi.spec.model.v30.EitherModelOrReferenceObject;
import com.prot.apitool.openapi.spec.model.v30.ReferenceObject;
import com.prot.apitool.openapi.spec.model.v30.SpecModel;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class EitherModelOrReferenceObjectDeserializer extends JsonDeserializer<EitherModelOrReferenceObject>
        implements ContextualDeserializer {
    private JavaType type;

    public EitherModelOrReferenceObjectDeserializer() {
    }

    public EitherModelOrReferenceObjectDeserializer(JavaType type) {
        this.type = type;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JavaType type = ctxt.getContextualType();
        if (type == null) {
            type = property.getMember().getType();
        }
        return new EitherModelOrReferenceObjectDeserializer(type);
    }

    @Override
    public EitherModelOrReferenceObject deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        JsonNode tree = ctx.readTree(p);
        if (tree == null) {
            return null;
        } else if (tree.has("$ref")) {
            ReferenceObject referenceObject = ((ObjectMapper)p.getCodec()).treeToValue(tree, ReferenceObject.class);
            return EitherModelOrReferenceObject.referenceObject(referenceObject);
        } else {        // Specific model object
            Class<SpecModel> wrappedModelClass = resolveWrappedModel();
            SpecModel model = ((ObjectMapper)p.getCodec()).treeToValue(tree, wrappedModelClass);
            return EitherModelOrReferenceObject.modelObject(model);
        }
    }

    private Class<SpecModel> resolveWrappedModel() {
        int len = type.getBindings().size();
        for (int i = 0; i < len; i++) {
            Class<SpecModel> ret = doResolveWrappedModel(type.getBindings().getBoundType(0));
            if (ret != null) {
                return ret;
            }
        }
        throw new RuntimeException("Fail to find SpecModel type");
    }

    private Class<SpecModel> doResolveWrappedModel(JavaType jType) {
        if (jType != null && jType.getRawClass() != null && SpecModel.class.isAssignableFrom(jType.getRawClass())) {
            return (Class<SpecModel>) jType.getRawClass();
        } else {
            return null;
        }
    }
}
