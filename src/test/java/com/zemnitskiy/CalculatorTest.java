package com.zemnitskiy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    void plus() {
        int expect = 4;
        int result = calculator.plus(2,2);
        assertEquals(expect, result);
        System.out.println("Test 1 passed");
    }

    @Test
    void minus() {
        int expect = 0;
        int result = calculator.minus(2,2);
        assertEquals(expect, result);
        System.out.println("Test 2 passed");
    }
    @Test
    void multiply() {
        int expect = 10;
        int result = calculator.multiply(5,2);
        assertEquals(expect, result);
        System.out.println("Test 3 passed");
    }
}