package com.prot.apitool.openapi.spec.model.v30;

import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfoObject extends SpecExtension implements SpecModel {
    @NotNull
    private String title;
    @CommonMarkSyntax
    private String description;
    private String termsOfService;
    private ContactObject contact;
    private LicenseObject license;
    @NotNull
    private String version;
}
