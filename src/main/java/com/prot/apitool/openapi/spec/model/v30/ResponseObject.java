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
public class ResponseObject extends SpecExtension implements SpecModel {
    @NotNull
    private String description;
//    private Map<String, ReferableHeaderObject> headers;
//    private Map<String, MediaTypeObject> content;
//    private Map<String, ReferableLinkObject> links;
}
