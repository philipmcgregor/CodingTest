package com.philipmcgregor.weatherapp.model;


public class SimpleLocation {

    double longitude;
    double latitude;

    /**
     * A lightweight DTO to represent a location
     * @param latitude
     * @param longitude
     */
    public SimpleLocation(double latitude,double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public SimpleLocation() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "SimpleLocation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
