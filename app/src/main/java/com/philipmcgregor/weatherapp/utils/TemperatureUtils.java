package com.philipmcgregor.weatherapp.utils;


public class TemperatureUtils {

    // Converts to celcius
    public static double convertFahrenheitToCelcius(double fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
}
