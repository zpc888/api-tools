package com.prot.apitool.openapi.spec.model.v30;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class PatternedFieldsModelWithExtension<T>
        extends LinkedHashMap<String, T> implements ISpecExtension, SpecModel {
    private List<ExtensionProperty> extensionProperties;
}
