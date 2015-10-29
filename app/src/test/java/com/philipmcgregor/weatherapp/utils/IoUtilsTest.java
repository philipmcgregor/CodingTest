package com.philipmcgregor.weatherapp.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;


public class IoUtilsTest {

    @Test
    public void testReadStreamConvertToString() throws Exception {

        String testData = "TEST DATA";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        String result = IoUtils.readStreamConvertToString(in);

        assertEquals("Check result",testData ,result);
    }


    @Test
    public void testReadStreamConvertToString_EmptyString() throws Exception {

        String testData = "";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        String result = IoUtils.readStreamConvertToString(in);

        assertEquals("Check result",testData ,result);
    }

}