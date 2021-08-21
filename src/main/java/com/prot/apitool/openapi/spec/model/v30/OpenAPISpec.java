package com.prot.apitool.openapi.spec.model.v30;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OpenAPISpec extends SpecExtension implements SpecModel {
    @NotNull
    private String openapi;
    @NotNull
    private InfoObject info;
    private List<ServerObject> servers;
    @NotNull
    private PathsObject paths;
    private ComponentsObject components;
    private List<SecurityRequirementObject> security;
    private List<TagObject> tags;
    private ExternalDocumentationObject externalDoc;
}
