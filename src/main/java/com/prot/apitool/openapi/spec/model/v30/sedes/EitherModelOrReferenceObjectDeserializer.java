package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.*;
import com.prot.apitool.openapi.spec.model.v30.EitherModelOrReferenceObject;
import com.prot.apitool.openapi.spec.model.v30.ReferenceObject;
import com.prot.apitool.openapi.spec.model.v30.SpecModel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public class EitherModelOrReferenceObjectDeserializer extends JsonDeserializer<EitherModelOrReferenceObject> {
    @Override
    public EitherModelOrReferenceObject deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        JsonNode tree = ctx.readTree(p);
        if (tree == null) {
            return null;
        } else if (tree.has("$ref")) {
            ReferenceObject referenceObject = ((ObjectMapper)p.getCodec()).treeToValue(tree, ReferenceObject.class);
            return EitherModelOrReferenceObject.referenceObject(referenceObject);
        } else {        // Specific model object
            Object currValueObj = p.getCurrentValue();
            String propName = p.getCurrentName();
            JsonStreamContext parsingContext = null;
            int nestedLevel = 0;
            // TODO: enhance for Map-of-Map-of-... cases, right now it tests with 0070-media-object.json
            while (currValueObj == null) {
                if (parsingContext == null) {
                    parsingContext = p.getParsingContext();
                } else {
                    parsingContext = parsingContext.getParent();
                    nestedLevel ++;
                }
                currValueObj = parsingContext.getCurrentValue();
                propName = parsingContext.getCurrentName();
            }
            Class parentClass = currValueObj.getClass();
            return resolveModel(parentClass, propName, tree, p);
        }
    }

    private Class<SpecModel> doResolve(ParameterizedType fieldType) {
        Type[] actualTypes = fieldType.getActualTypeArguments();
        for (int i = 0; i < actualTypes.length; i++) {
            Type actualType = actualTypes[i];
            if (actualType instanceof ParameterizedType) {
                // TODO: not perfect yet
                Class<SpecModel> ret = doResolve((ParameterizedType)actualType);
                if (ret != null) {
                    return ret;
                }
                // else if actualType instanceof WildcardType
                // else if actualType instanceof GenericArrayType
                // else if actualType instanceof TypeVariable
            } else {            // Class
                if (SpecModel.class.isAssignableFrom((Class) actualType)) {
                    return (Class<SpecModel>) actualType;
                }
            }
        }
        return null;
    }

    EitherModelOrReferenceObject resolveModel(Class<?> clazz, String propName, JsonNode tree, JsonParser p) throws IOException {
        try {
            Field f = clazz.getDeclaredField(propName);
            Type fieldType = f.getGenericType();
            if (!(fieldType instanceof ParameterizedType)) {
                throw new IOException("it is not generic type for field: [" + propName + "] for class: [" + clazz + "]");
            }
            Class<SpecModel> actualType = doResolve( (ParameterizedType)fieldType );
            if (actualType == null) {
                throw new IOException("it is not SpecModel type for field: [" + propName + "] for class: [" + clazz + "]");
            }
            SpecModel model = ((ObjectMapper)p.getCodec()).treeToValue(tree, actualType);
            return EitherModelOrReferenceObject.modelObject(model);
        } catch (NoSuchFieldException e) {
            Class<?> parentClass = clazz.getSuperclass();
            if (parentClass == Object.class) {
                throw new IOException("no such field: [" + propName + "] for class: [" + clazz + "]");
            } else {
                return resolveModel(parentClass, propName, tree, p);
            }
        }
    }
}
