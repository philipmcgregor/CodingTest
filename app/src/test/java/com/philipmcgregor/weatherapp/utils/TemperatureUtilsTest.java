package com.philipmcgregor.weatherapp.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TemperatureUtilsTest {

    @Test
    public void testConvertFahrenheitToCelcius() throws Exception {

        double result = TemperatureUtils.convertFahrenheitToCelcius(32d);

        assertEquals(0,result,0);
    }
}