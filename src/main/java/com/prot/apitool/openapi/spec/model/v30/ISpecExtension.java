package com.prot.apitool.openapi.spec.model.v30;

import java.util.List;

public interface ISpecExtension {
    List<ExtensionProperty> getExtensionProperties();

    default boolean hasExtension() {
        return getExtensionProperties() != null && !getExtensionProperties().isEmpty();
    }
}
