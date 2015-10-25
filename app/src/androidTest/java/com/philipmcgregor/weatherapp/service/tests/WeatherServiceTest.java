package com.philipmcgregor.weatherapp.service.tests;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.philipmcgregor.weatherapp.model.LocationWeather;
import com.philipmcgregor.weatherapp.model.SimpleLocation;
import com.philipmcgregor.weatherapp.service.WeatherService;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class WeatherServiceTest extends InstrumentationTestCase {

    //private WeatherService mainActivity;
    private static boolean serviceCalled;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        serviceCalled = false;
    }

    public void testWeatherWebService() throws Throwable {

        // create a signal to indicate when task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                final WeatherService weatherService = new WeatherService(){
                    @Override
                    protected void onPostExecute(LocationWeather result) {
                        super.onPostExecute(result);
                        Log.i("TEST", "result = " + result);

                        serviceCalled = !result.getResponse().toString().equals("{}");

                        //Signal AsyncTask has returned
                        signal.countDown();
                    }
                };

                    SimpleLocation location = new SimpleLocation();
                    location.setLatitude(37.8267);
                    location.setLongitude(-122.423);
                    weatherService.execute(location);
            }
        });

        //wait here until the UI thread calls signal.countDown() or 15 seconds passes
        signal.await(15, TimeUnit.SECONDS);

        assertTrue(serviceCalled);
    }

    public void testWeatherWebService_badLocation() throws Throwable {

        // create a signal to indicate when task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                final WeatherService weatherService = new WeatherService(){
                    @Override
                    protected void onPostExecute(LocationWeather result) {
                        super.onPostExecute(result);
                        Log.i("TEST", "result = " + result);

                        serviceCalled = result.getResponse().toString().equals("{}");

                        //Signal AsyncTask has returned
                        signal.countDown();
                    }
                };

                SimpleLocation location = new SimpleLocation();
                location.setLatitude(12121237.8267);
                location.setLongitude(-13434322.423);
                weatherService.execute(location);
            }
        });

        //wait here until the UI thread calls signal.countDown() or 15 seconds passes
        signal.await(15, TimeUnit.SECONDS);

        assertTrue(serviceCalled);
    }




}