package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContactObject extends SpecExtension implements SpecModel {
    private String name;
    private String url;
    private String email;
}
