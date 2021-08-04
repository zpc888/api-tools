package com.prot.apitool.mock.model;

import java.util.List;

public class RoundTrip {
    private Long id;
    private String requestPath;      // PathPatternParser
    private String method;           // regex ?
    private List<MockDispatch> dispatches;
}
