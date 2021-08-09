package com.prot.apitool;

import org.junit.jupiter.api.Test;
import org.springframework.http.server.PathContainer;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PathPatternMatcher {
    @Test
    public void testMatchInfo01() {
        verifyMatchInfo("/api/**", "/api/a", Collections.emptyMap(), Collections.emptyMap());
        verifyMatchInfo("/api/**", "/api/a/b/c", Collections.emptyMap(), Collections.emptyMap());
    }
    @Test
    public void testMatchInfo02() {
        verifyMatchInfo("/api/{x}/**", "/api/a", Collections.singletonMap("x", "a"), Collections.emptyMap());
        verifyMatchInfo("/api/{x}/**", "/api/a/b/c", Collections.singletonMap("x", "a"), Collections.emptyMap());
    }
    @Test
    public void testMatchInfo03() {
        verifyMatchInfo("/api/hi?fname={firstName}&lname={lastName}", "/api/hi?fname=george&lname=Zhou",
                Map.of("firstName", "george", "lastName", "Zhou"), Collections.emptyMap());
        verifyMatchInfo("/api/hi?name={firstName}_{lastName}", "/api/hi?name=george_Zhou",
                Map.of("firstName", "george", "lastName", "Zhou"), Collections.emptyMap());
        // not working since query param never be grouped
//        verifyMatchInfo("/api/hi?fname={firstName}&lname={lastName}", "/api/hi?fname=george&lname=Zhou&fname=abc",
//                Map.of("firstName", "george", "lastName", "Zhou&fname=abc"), Collections.emptyMap());
        // not working since query param has the order
//        verifyMatchInfo("/api/hi?fname={firstName}&lname={lastName}", "/api/hi?lname=Zhou&fname=george",
//                Map.of("firstName", "george", "lastName", "Zhou"), Collections.emptyMap());
    }

    @Test
    public void testMatchInfo04() {
        verifyMatchInfo("/api/{v1}_{v2}", "/api/abc_efg",
                Map.of("v1", "abc", "v2", "efg"), Collections.emptyMap());
    }

    private void verifyMatchInfo(String patternStr, String path, Map<String, String> uriVars, Map<String, MultiValueMap<String, String>> matrixVars) {
        PathPatternParser parser = PathPatternParser.defaultInstance;
        PathPattern pattern = parser.parse(patternStr);

        PathContainer container = PathContainer.parsePath(path);
        PathPattern.PathMatchInfo matchInfo = pattern.matchAndExtract(container);
        assertEquals(uriVars.size(), matchInfo.getUriVariables().size());
        assertEquals(matrixVars.size(), matchInfo.getMatrixVariables().size());
        for (String k: uriVars.keySet()) {
            assertEquals(uriVars.get(k), matchInfo.getUriVariables().get(k));
        }
        for (String k: matrixVars.keySet()) {
            assertEquals(matrixVars.get(k), matchInfo.getMatrixVariables().get(k));
        }
    }

    @Test
    public void testMatches() {
        PathPatternParser parser = PathPatternParser.defaultInstance;
        PathPattern pattern = parser.parse("/api/**");
        assertTrue(pattern.matches(PathContainer.parsePath("/api/abc/efg")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api/abc")));
        assertFalse(pattern.matches(PathContainer.parsePath("/api-v1/abc/efg")));

        pattern = parser.parse("/api/*");
        assertFalse(pattern.matches(PathContainer.parsePath("/api/abc/efg")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api/abc")));
        assertFalse(pattern.matches(PathContainer.parsePath("/api-v1/abc/efg")));

        pattern = parser.parse("/{apiTag:api(?:-v1)?}/*");
        assertFalse(pattern.matches(PathContainer.parsePath("/api/abc/efg")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api/abc")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api-v1/abc")));

        pattern = parser.parse("/{apiTag:(?:api|api-v1)}/**");
        assertFalse(pattern.matches(PathContainer.parsePath("/apiv1/abc/efg")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api/abc/efg")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api/abc")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api-v1/abc")));
        assertTrue(pattern.matches(PathContainer.parsePath("/api-v1/abc/efg")));
    }

    @Test
    public void testNotMatch() {
        PathPatternParser parser = PathPatternParser.defaultInstance;
        PathPattern pattern = parser.parse("/{internalApiPrefix:(?!(?:api|admin)).*}/**");
        assertFalse(pattern.matches(PathContainer.parsePath("/api")));
        assertFalse(pattern.matches(PathContainer.parsePath("/admin")));
        assertFalse(pattern.matches(PathContainer.parsePath("/api/abc")));
        assertFalse(pattern.matches(PathContainer.parsePath("/admin/abc")));
        assertFalse(pattern.matches(PathContainer.parsePath("/api/abc/efg")));
        assertFalse(pattern.matches(PathContainer.parsePath("/admin/abc/efg")));

        assertTrue(pattern.matches(PathContainer.parsePath("/abc")));
        assertTrue(pattern.matches(PathContainer.parsePath("/abc/efg")));
        assertTrue(pattern.matches(PathContainer.parsePath("/a/b/c")));
    }
}
