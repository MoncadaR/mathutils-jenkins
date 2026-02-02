package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    private MathUtils math;

    @BeforeEach
    void setUp() {
        math = new MathUtils();
    }

    @Test
    void testAdd() {
        assertEquals(5, math.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, math.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(12, math.multiply(3, 4));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, math.divide(5, 2), 1e-9);
    }

    @Test
    void testDivideByZero() {
        assertEquals(-1.0, math.divide(5, 0), 1e-9);
    }
}
