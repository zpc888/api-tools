package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OAuthFlowsObject  extends SpecExtension implements SpecModel {
    // configuration for the OAuth implicit flow
    private OAuthFlowObject implicit;
    // configuration for the OAuth Resource Owner Password flow
    private OAuthFlowObject password;
    // configuration for the OAuth Client Credentials flow. Previously called application in OpenAPI 2.0
    private OAuthFlowObject clientCredentials;
    // configuration for the OAuth Authorization Code flow. Previously called accessCode in OpenAPI 2.0
    private OAuthFlowObject authorizationCode;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class OAuthFlowObject extends SpecExtension implements SpecModel {
        // applies to oauth2("implicit", "authorizationCode")
        // Required. The authorization URL to be used for this flow. This MUST be in the form of a URL
        @NotNull
        private String authorizationUrl;

        // applies to oauth2("password", "clientCredentials", "authorizationCode")
        // Required. The authorization URL to be used for this flow. This MUST be in the form of a URL
        @NotNull
        private String tokenUrl;

        // applies to oauth2
        // The URL to be used for obtaining refresh tokens. This MUST be in the form of a URL
        private String refreshUrl;

        // applies to oauth2
        // Required. The available scopes for OAuth2 security scheme. A map between the scope name and a short description for it.
        // The map MAY be empty.
        @NotNull
        private Map<String, String> scopes = new LinkedHashMap<>();
    }
}
