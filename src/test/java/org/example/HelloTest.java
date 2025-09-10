package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloTest {

    @Test
    void testSum() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 debería ser 5");
    }
}
