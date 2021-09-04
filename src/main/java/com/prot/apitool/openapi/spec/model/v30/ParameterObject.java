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
public class ParameterObject extends AbstractParameterValueObject {
    enum InLocation {
        query, header, path, cookie
    }
    @NotNull
    private String name;
    @NotNull
    private InLocation in;
}
