package com.sda.service;

import com.sda.services.StringCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @Before
    public void init() {
        this.calculator = new StringCalculator();
    }

    @Test
    public void testSingleNumber() {
        //given
        String value = "5";
        //when
        int result = calculator.calculate(value);
        //then
        Assert.assertEquals(5, result);
    }

    @Test
    public void testMultipleNumbers() {
        //given
        String value = "1;2;3;4;5;6";
        //when
        int result = calculator.calculate(value);
        //then
        Assert.assertEquals(21, result);
    }

    @Test
    public void testMultipleNumbersWithWhitespaces() {
        //given
        String value = "1;2     ;3 ;4 ;5\n;6";
        //when
        int result = calculator.calculate(value);
        //then
        Assert.assertEquals(21, result);
    }

    @Test
    public void testCalculationForBlankValue() {
        //given
        String value = "";
        //when
        int result = calculator.calculate(value);
        //then
        Assert.assertEquals(0, result);
    }

    @Test
    public void testCalculationForNullValue() {
        //given
        String value = null;
        //when
        int result = calculator.calculate(value);
        //then
        Assert.assertEquals(0, result);
    }

    @Test
    public void testSimpleCalculation() {
        Assert.assertEquals(5, calculator.calculate("2;3"));
        Assert.assertEquals(6, calculator.calculate("1;5"));
        Assert.assertEquals(17, calculator.calculate("8;9"));
        Assert.assertEquals(35, calculator.calculate("15;20"));
    }
}

