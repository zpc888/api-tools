package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public abstract class SpecExtension implements ISpecExtension {
    private List<ExtensionProperty> extensionProperties;
}
