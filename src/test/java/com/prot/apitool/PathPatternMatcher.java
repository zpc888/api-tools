package com.prot.apitool;

import org.junit.jupiter.api.Test;
import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathPatternMatcher {
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
