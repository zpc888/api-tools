package com.prot.apitool.mock.dto;

import java.util.List;

public interface UriParsedResult {
    boolean matched();
    String path(String var);
    List<String> paths(String var);
    String param(String var);
    List<String> params(String var);
}
