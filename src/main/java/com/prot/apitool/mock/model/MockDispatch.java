package com.prot.apitool.mock.model;

public class MockDispatch {
    private Long id;
    private Long roundTripId;
    private Integer priority = 50;
    // paths.${path_var}  params.${param_name} headers.${header_name}  cookies.${cookie_name}
    // auths.jwt.${jwt_json}    request.json.${json_path}   now
    private String conditionExpr;
    private String responseTemplate;
}