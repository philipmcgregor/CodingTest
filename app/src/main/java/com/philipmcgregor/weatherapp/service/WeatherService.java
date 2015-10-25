package com.philipmcgregor.weatherapp.service;

import android.os.AsyncTask;
import android.util.Log;

import com.philipmcgregor.weatherapp.model.LocationWeather;
import com.philipmcgregor.weatherapp.model.SimpleLocation;
import com.philipmcgregor.weatherapp.utils.IoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService extends AsyncTask<SimpleLocation, Void, LocationWeather> {

    private static String TAG = "WEATHER_SERVICE";
    private static String WEATHER_SERVICE_API_KEY = "f2feea55f3a992ac9c5de544de9c1dfb";
    //https://api.forecast.io/forecast/f2feea55f3a992ac9c5de544de9c1dfb/37.8267,-122.423
    private static String WEATHER_SERVICE_URL = "https://api.forecast.io/forecast/" + WEATHER_SERVICE_API_KEY + "/";

    @Override
    protected LocationWeather doInBackground(SimpleLocation... locations) {

        if (locations.length != 1) {
            throw new IllegalArgumentException("Expecting one location");
        }

        LocationWeather locationWeather = new LocationWeather();
        locationWeather.setResponse(buildJsonObject(callWeatherService(locations[0])));

        return locationWeather;
    }

    private String callWeatherService(SimpleLocation location) {
        HttpURLConnection conn = null;
        String webServiceResponse = "";
        try {
            URL url = new URL(WEATHER_SERVICE_URL + location.getLatitude() + "," + location.getLongitude() );
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            System.out.println("Response Code: " + conn.getResponseCode());

            if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                webServiceResponse = IoUtils.readStreamConvertToString(in);
                in.close();
                Log.d(TAG, "result = " + webServiceResponse);
            }else{
                Log.e(TAG, "unexpected response code = " + conn.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return webServiceResponse;
    }

    private JSONObject buildJsonObject(String webServiceResponse) {
        JSONObject resultJSON;
        try {
            resultJSON = new JSONObject(webServiceResponse);
        } catch (JSONException e) {
            resultJSON = new JSONObject();
            e.printStackTrace();
        }
        return resultJSON;
    }





}