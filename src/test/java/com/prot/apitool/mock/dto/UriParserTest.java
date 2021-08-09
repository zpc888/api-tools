package com.prot.apitool.mock.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UriParserTest {

    @Test
    void pathOnly() {
        UriParser parser = new UriParser("/");
        assertEquals("/", parser.requestUriPattern);
        assertEquals("/", parser.requestPathPart);
        assertEquals(0, parser.requestParamParts.size());

        parser = new UriParser("/a/b/c");
        assertEquals("/a/b/c", parser.requestUriPattern);
        assertEquals("/a/b/c", parser.requestPathPart);
        assertEquals(0, parser.requestParamParts.size());

        parser = new UriParser("/a/b/c?one");
        assertEquals("/a/b/c?one", parser.requestUriPattern);
        assertEquals("/a/b/c", parser.requestPathPart);
        assertEquals(1, parser.requestParamParts.size());
        assertEquals("", parser.requestParamParts.get("one"));

        parser = new UriParser("/a/b/c?one=1");
        assertEquals("/a/b/c?one=1", parser.requestUriPattern);
        assertEquals("/a/b/c", parser.requestPathPart);
        assertEquals(1, parser.requestParamParts.size());
        assertEquals("1", parser.requestParamParts.get("one"));

        parser = new UriParser("/a/b/c?one=1&two=2");
        assertEquals("/a/b/c?one=1&two=2", parser.requestUriPattern);
        assertEquals("/a/b/c", parser.requestPathPart);
        assertEquals(2, parser.requestParamParts.size());
        assertEquals("1", parser.requestParamParts.get("one"));
        assertEquals("2", parser.requestParamParts.get("two"));

        parser = new UriParser("/a/{b}/{c}?one={x}&two={y}");
        assertEquals("/a/{b}/{c}", parser.requestPathPart);
        assertEquals(2, parser.requestParamParts.size());
        assertEquals("{x}", parser.requestParamParts.get("one"));
        assertEquals("{y}", parser.requestParamParts.get("two"));
    }
}