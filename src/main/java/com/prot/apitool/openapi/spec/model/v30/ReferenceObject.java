package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReferenceObject implements SpecModel {
    public ReferenceObject() {
    }

    public ReferenceObject(String ref) {
        this.ref = ref;
    }

    @NotNull
    @JsonProperty("$ref")
    private String ref;
}
