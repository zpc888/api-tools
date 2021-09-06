package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecuritySchemeObject extends SpecExtension implements SpecModel {
    enum SecuritySchemeType {
        apiKey, http, oauth2, openIdConnect
    }
    enum ApiKeyLocation {
        query, header, cookie
    }
    @NotNull
    private SecuritySchemeType type;
    @CommonMarkSyntax
    private String description;

    // conditionalRequired, when type = apiKey
    private String name;
    // conditionalRequired, when type = apiKey
    private ApiKeyLocation in;

    // conditionalRequired, when type = http
    private String scheme;          // RFC7235
    private String bearerFormat;    // only meaningful when type = http

    // conditionalRequired when type = oauth2
    private OAuthFlowsObject flows;

    // conditionalRequired when type = openIdConnect
    private String openIdConnectUrl;
}
