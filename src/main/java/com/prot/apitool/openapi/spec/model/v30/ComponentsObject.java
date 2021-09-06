package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComponentsObject extends SpecExtension implements SpecModel {
    private Map<String, EitherModelOrReferenceObject<SchemaObject>> schemas;
    private Map<String, EitherModelOrReferenceObject<ResponseObject>> responses;
    private Map<String, EitherModelOrReferenceObject<ParameterObject>> parameters;
    private Map<String, EitherModelOrReferenceObject<ExampleObject>> examples;
    private Map<String, EitherModelOrReferenceObject<RequestBodyObject>> requestBodies;
    private Map<String, EitherModelOrReferenceObject<HeaderObject>> headers;
    private Map<String, EitherModelOrReferenceObject<SecuritySchemeObject>> securitySchemas;
    private Map<String, EitherModelOrReferenceObject<LinkObject>> links;
//    private Map<String, ReferableCallbackObject> callbacks;
}
