package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prot.apitool.openapi.spec.model.v30.sedes.EitherModelOrReferenceObjectDeserializer;
import com.prot.apitool.openapi.spec.model.v30.sedes.EitherModelOrReferenceObjectSerializer;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;


@JsonDeserialize(using = EitherModelOrReferenceObjectDeserializer.class)
@JsonSerialize(using = EitherModelOrReferenceObjectSerializer.class)
@Data
public final class EitherModelOrReferenceObject<T extends SpecModel> implements SpecModel {
    @Nullable
    private T modelObject;
    @Nullable
    private ReferenceObject referenceObject;

    public static <X extends SpecModel> EitherModelOrReferenceObject<X> modelObject(X modelObject) {
        return new EitherModelOrReferenceObject<>(modelObject, null);
    }

    public static <X extends SpecModel> EitherModelOrReferenceObject<X> referenceObject(ReferenceObject referenceObject) {
        return new EitherModelOrReferenceObject<>(null, referenceObject);
    }

    public EitherModelOrReferenceObject() {
    }

    public EitherModelOrReferenceObject(T modelObject, ReferenceObject referenceObject) {
        this.modelObject = modelObject;
        this.referenceObject = referenceObject;
    }

    @JsonIgnore
    public boolean isReferenceObject() {
        return referenceObject != null && modelObject == null;
    }

    @JsonIgnore
    public boolean isModelObject() {
        return referenceObject == null && modelObject != null;
    }

    public void setModelObject(@NotNull T modelObject) {
        this.modelObject = modelObject;
        this.referenceObject = null;
    }

    public void setReferenceObject(@NotNull ReferenceObject referenceObject) {
        this.referenceObject = referenceObject;
        this.modelObject = null;
    }
}
