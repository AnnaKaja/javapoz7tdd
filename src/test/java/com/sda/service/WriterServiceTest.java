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
}
