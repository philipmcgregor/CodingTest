package com.philipmcgregor.weatherapp.model;


import org.json.JSONObject;

import java.text.DecimalFormat;

public class LocationWeather {

    private JSONObject response;
    private String summary;
    private WeatherType weatherType;
    private double temperature;
    public static final String DEGREE = "\u00b0";
    DecimalFormat df = new DecimalFormat("0.00");

    public LocationWeather() {
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public String getSummary() {
        return summary;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getTemperatureAsString() {
        return df.format(temperature) + " " + DEGREE;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
