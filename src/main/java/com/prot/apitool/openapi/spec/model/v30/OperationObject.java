package com.prot.apitool.openapi.spec.model.v30;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OperationObject  extends SpecExtension implements SpecModel {
    private List<String>  tags;
    private String summary;
    @CommonMarkSyntax
    private String description;
    private ExternalDocumentationObject externalDocs;
    private String operationId;
    private List<EitherModelOrReferenceObject<ParameterObject>> parameters;
    private EitherModelOrReferenceObject<RequestBodyObject> requestBody;
    @NotNull
    private ResponsesObject responses;
    private Map<String, EitherModelOrReferenceObject<CallbackObject>> callbacks;
    private Boolean deprecated;
    private List<SecurityRequirementObject> security;
    private List<ServerObject> servers;
}
