package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComponentsObject extends SpecExtension implements SpecModel {
//    private Map<String, ReferableSchemaObject> schemas;
//    private Map<String, ReferableResponseObject> responses;
//    private Map<String, ReferableParameterObject> parameters;
//    private Map<String, ReferableExamplesObject> examples;
//    private Map<String, ReferableRequestBodyObject> requestBodies;
//    private Map<String, ReferableHeaderObject> headers;
//    private Map<String, ReferableSecuritySchemaObject> securitySchemas;
//    private Map<String, ReferableLinkObject> links;
//    private Map<String, ReferableCallbackObject> callbacks;
}
