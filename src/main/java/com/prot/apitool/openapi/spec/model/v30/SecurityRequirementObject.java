package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityRequirementObject extends PatternedFieldsModelWithExtension<List<String>> implements SpecModel {
    // key is {name}
    // value is [string]
    // Each name MUST correspond to a security scheme which is declared in the Security Schemes under the Components Object.
    // If the security scheme is of type "oauth2" or "openIdConnect", then the value is a list of scope names required for
    // the execution, and the list MAY be empty if authorization does not require a specified scope.
    // For other security scheme types, the array MUST be empty.
}
