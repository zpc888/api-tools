package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExampleObject extends SpecExtension implements SpecModel {
    private String summary;
    @CommonMarkSyntax
    private String description;
    // exclusive between value field and externalValue field
    private AnyObject value;
    private String externalValue;
}
