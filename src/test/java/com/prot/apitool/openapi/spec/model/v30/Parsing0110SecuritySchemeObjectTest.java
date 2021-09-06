package com.prot.apitool.openapi.spec.model.v30;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Parsing0110SecuritySchemeObjectTest extends AbstractParsingModelTest {
    private SecuritySchemeObject expectedResult;

    @BeforeEach
    void init() {
        expectedResult = new SecuritySchemeObject();
        expectedResult.setType(SecuritySchemeObject.SecuritySchemeType.oauth2);
        OAuthFlowsObject flows = new OAuthFlowsObject();
        expectedResult.setFlows(flows);

        OAuthFlowsObject.OAuthFlowObject implicitFlow = new OAuthFlowsObject.OAuthFlowObject();
        flows.setImplicit(implicitFlow);
        implicitFlow.setAuthorizationUrl("https://example.com/api/oauth/dialog");
        implicitFlow.getScopes().put("write:pets", "modify pets in your account");
        implicitFlow.getScopes().put("read:pets", "read your pets");

        OAuthFlowsObject.OAuthFlowObject authorizationCodeFlow = new OAuthFlowsObject.OAuthFlowObject();
        flows.setAuthorizationCode(authorizationCodeFlow);
        authorizationCodeFlow.setAuthorizationUrl("https://example.com/api/oauth/dialog");
        authorizationCodeFlow.setTokenUrl("https://example.com/api/oauth/token");
        authorizationCodeFlow.getScopes().put("write:pets", "modify pets in your account");
        authorizationCodeFlow.getScopes().put("read:pets", "read your pets");
    }

    @Test
    void testParseFromYaml() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0110-security-scheme-object.yaml";
        verifyParseYaml(expectedResult, resourceName, SecuritySchemeObject.class);
    }

    @Test
    void testParseFromJson() throws Exception {
        final String resourceName = "openapi-spec-model-samples/0110-security-scheme-object.json";
        verifyParseJson(expectedResult, resourceName, SecuritySchemeObject.class);
    }
}
