package com.prot.apitool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSplitTest {
    @Test
    public void testSplitWithLimit() {
        final String str = "boo:and:foo";
        verify(str.split(":", 2), "boo", "and:foo");
    }

    private void verify(String[] split, String... expected) {
        assertEquals(expected.length, split.length);
        for (int i = 0; i < split.length; i++) {
            assertEquals(expected[i], split[i]);
        }
    }
}
