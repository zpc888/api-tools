package com.prot.apitool.openapi.spec.model.v30;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MediaTypeObject extends SpecExtension implements SpecModel {
    private EitherModelOrReferenceObject<SchemaObject> schema;
    private AnyObject example;
    private Map<String, EitherModelOrReferenceObject<ExampleObject>> examples;
    private Map<String, EncodingObject> encoding;
}
