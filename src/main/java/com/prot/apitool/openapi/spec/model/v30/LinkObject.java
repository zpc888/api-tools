package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LinkObject extends SpecExtension implements SpecModel {
    // exclusive between operationRef and operationId
    private String operationRef;
    private String operationId;
    private Map<String, AnyOrExpressionObject> parameters;
    private AnyOrExpressionObject requestBody;
    @CommonMarkSyntax
    private String description;
    private ServerObject server;
}
