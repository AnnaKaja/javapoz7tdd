package com.sda.service;

import com.sda.services.WriterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WriterServiceTest {
    private WriterService writerService;

    @Before
    public void init() {
        this.writerService = new WriterService();
    }

    @Test
    public void testOneName() {
        //given

        String name = "Anna";

        //when
        String result = writerService.write(name);

        //then
        Assert.assertEquals("Hello, Anna.", result);
    }

    @Test
    public void testNullName() {
        //given
        String name = null;
        //when
        String result = writerService.write(name);

        //then
        Assert.assertEquals("Hello, my friend.", result);
    }


    @Test
    public void testBlankName() {
        //given
        String name = "";

        //when
        String result = writerService.write(name);

        //then
        Assert.assertEquals("Hello, my friend.", result);
    }

    @Test
    public void testEmptyName() {
        //given
        String name = "   ";

        //when
        String result = writerService.write(name);

        //then
        Assert.assertEquals("Hello, my friend.", result);
    }
    public void testCapitalizedName() {
        //given
        String name = "ANNA";
        //when
        String result = writerService.write(name);

        //then
        Assert.assertEquals("HELLO, ANNA!", result);
    }


    @Test
    public void testMultipleNames() {
        //given
        String value = "Szymon,Anna,Jan";

        //when
        String result = writerService.write(value);

        //then
        Assert.assertEquals("Hello, Szymon, Anna and Jan.", result);
    }

    @Test
    public void testMultipleCapitalizedNames() {
        //given
        String value = "SZYMON,ANNA,JAN";

        //when
        String result = writerService.write(value);

        //then
        Assert.assertEquals("HELLO, SZYMON, ANNA AND JAN!", result);
    }
}
