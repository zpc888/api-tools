package com.prot.apitool;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeMatchWords {
    @Test
    void testMatchWords() {
        String pattern = "/(admin|api).*";
        Pattern p = Pattern.compile(pattern);
        assertTrue(p.matcher("/admin").matches());
        assertTrue(p.matcher("/admin/asf").matches());
        assertTrue(p.matcher("/api").matches());
        assertTrue(p.matcher("/api/").matches());
        assertTrue(p.matcher("/api?a=b&b=3").matches());
        assertFalse(p.matcher("/abc").matches());
        assertFalse(p.matcher("/abc/adf").matches());
    }

    @Test
    void testNotMatchWords() {
        String pattern = "/(?!(admin|api)).*";
        Pattern p = Pattern.compile(pattern);
        assertFalse(p.matcher("/admin").matches());
        assertFalse(p.matcher("/admin/asf").matches());
        assertFalse(p.matcher("/api").matches());
        assertFalse(p.matcher("/api/").matches());
        assertFalse(p.matcher("/api?a=b&b=3").matches());
        assertTrue(p.matcher("/abc").matches());
        assertTrue(p.matcher("/abc/adf").matches());
    }

    @Test
    void testNotMatchWordsWithNoCapturedGroup() {
        String pattern = "/(?!(?:admin|api)).*";
        Pattern p = Pattern.compile(pattern);
        assertFalse(p.matcher("/admin").matches());
        assertFalse(p.matcher("/admin/asf").matches());
        assertFalse(p.matcher("/api").matches());
        assertFalse(p.matcher("/api/").matches());
        assertFalse(p.matcher("/api?a=b&b=3").matches());
        assertTrue(p.matcher("/abc").matches());
        assertTrue(p.matcher("/abc/adf").matches());
    }
}
