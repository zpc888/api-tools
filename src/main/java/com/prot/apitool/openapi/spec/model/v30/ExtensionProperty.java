package com.prot.apitool.openapi.spec.model.v30;

import lombok.Data;

@Data
public class ExtensionProperty {
    private String afterPropertyName;
    private String fieldPattern;
    private Object type;
}
