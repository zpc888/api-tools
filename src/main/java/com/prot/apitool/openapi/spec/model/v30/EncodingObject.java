package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EncodingObject extends SpecExtension implements SpecModel {
    private String contentType;
    private Map<String, EitherModelOrReferenceObject<HeaderObject>> headers;
    private String style;
    private Boolean explode;
    private Boolean allowReserved;
}
