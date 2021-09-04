package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prot.apitool.openapi.spec.model.v30.sedes.AnyObjectDeserializer;
import com.prot.apitool.openapi.spec.model.v30.sedes.AnyObjectSerializer;
import lombok.Data;

@JsonSerialize(using = AnyObjectSerializer.class)
@JsonDeserialize(using = AnyObjectDeserializer.class)
@Data
public class AnyObject implements SpecModel {
    private Object value;

    public AnyObject() {
    }

    public AnyObject(Object value) {
        this.value = value;
    }
}
