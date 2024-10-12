package sos.ryanbyers.sosjavafx;

import sos.ryanbyers.sosjavafx.BadCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BadCalculatorTest {
    private BadCalculator calc;

    @BeforeEach
    public void setUp(){
        calc = new BadCalculator();
    }

    @Test
    public void testAdd(){
        var result = calc.add(1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testSubtract(){
        var result = calc.subtract(5, 3);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testMultiply(){
        var result = calc.multiply(60, 67);
        Assertions.assertEquals(4020, result);
    }

    @Test
    public void testDivide(){
        var result = calc.divide(80, 2);
        Assertions.assertEquals(40, result);
    }

}
