package com.philipmcgregor.weatherapp.model;


import org.json.JSONObject;

public class LocationWeather {

    private JSONObject response;

    public LocationWeather() {
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }

}
