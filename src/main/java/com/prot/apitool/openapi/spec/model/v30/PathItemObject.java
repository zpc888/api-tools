package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PathItemObject  extends SpecExtension implements SpecModel {
    @JsonProperty("$ref")
    private String ref;
    private String summary;
    @CommonMarkSyntax
    private String description;
    private OperationObject get;
    private OperationObject put;
    private OperationObject post;
    private OperationObject delete;
    private OperationObject options;
    private OperationObject head;
    private OperationObject patch;
    private OperationObject trace;
    private List<ServerObject> servers;
    private List<EitherModelOrReferenceObject<ParameterObject>> parameters;
}
