package com.philipmcgregor.weatherapp.activity;

import android.app.Activity;
import android.content.Context;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.philipmcgregor.weatherapp.R;
import com.philipmcgregor.weatherapp.model.LocationWeather;
import com.philipmcgregor.weatherapp.model.SimpleLocation;
import com.philipmcgregor.weatherapp.service.WeatherService;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    public static String TAG = "WEATHER_TEST";

    private boolean canGetLocation = false;
    Location location;

    private TextView weatherServiceResponseTitle, weatherServiceResponse, forcastSummary;
    LocationListener locationListener;
    LocationManager locationManager;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000*60; // 1 minute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherServiceResponseTitle = (TextView) findViewById(R.id.weatherServiceResposneTitleTextView);
        weatherServiceResponse = (TextView) findViewById(R.id.weatherServiceResponseTextView);
        forcastSummary = (TextView) findViewById(R.id.forcastSummaryTextView);

        requestLocation();
    }

    public void setLocation(Location location){
        this.location = location;
    }


    private void requestLocation(){

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.v(TAG,"isGPSEnabled=" + isGPSEnabled);

        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.v(TAG, "isNetworkEnabled=" + isNetworkEnabled);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found
                Log.i(TAG,"Location retrieved - " + location.toString());
                setLocation(location);
                refreshWeatherData();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) { }
            public void onProviderEnabled(String provider) { }
            public void onProviderDisabled(String provider) { }
        };

        // Register the listener with the Location Manager to receive location updates
        try {

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                Log.i(TAG, "unable to get network or gps location");
            } else {
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }


        }catch(SecurityException e){
            e.printStackTrace();
        }
    }


    private void refreshWeatherData() {

            WeatherService weatherService = new WeatherService(){
                @Override
                protected void onPostExecute(LocationWeather result) {
                    Log.i(TAG, "result = " + result.getResponse().toString());
                    weatherServiceResponse.setText(result.getResponse().toString());
                    weatherServiceResponseTitle.setText(getString(R.string.weatherServiceResponseTitle));

                    JSONObject currently;
                    try {
                        currently = result.getResponse().getJSONObject("currently");
                        String summary = currently.getString("summary");
                        forcastSummary.setText(summary);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            weatherService.execute(new SimpleLocation(location.getLatitude(),location.getLongitude()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Remove the listener you previously added
        try {
            if (locationManager != null && locationListener != null){
                locationManager.removeUpdates(locationListener);
            }
        }catch(SecurityException e){
            Log.e(TAG, "locationManager.removeUpdates ", e);
        }

    }

}
