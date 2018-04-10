package com.sda.service;

import com.sda.services.StringCalculator;
import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void testSimpleCalculation() {
        //given
        StringCalculator calculator = new StringCalculator();

        //then
        Assert.assertEquals(5, calculator.calculate("2;3"));
        Assert.assertEquals(6, calculator.calculate("1;5"));
        Assert.assertEquals(17, calculator.calculate("8;9"));
        Assert.assertEquals(35, calculator.calculate("15;20"));
    }
}
