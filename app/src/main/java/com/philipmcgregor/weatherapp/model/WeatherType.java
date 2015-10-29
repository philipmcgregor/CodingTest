package com.philipmcgregor.weatherapp.model;


public enum WeatherType {

    /*
        clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
     */

    CLEAR_DAY("clear-day","clearday" ),
    CLEAR_NIGHT("clear-night","clearnight"),
    RAIN("rain", "rain"),
    SNOW("snow", "snow"),
    SLEET("sleet", "snow"),
    WIND("wind", "wind"),
    FOG("fog", "cloudy"),
    CLOUDY("cloudy", "cloudy"),
    PARTLY_CLOUDY_DAY(" partly-cloudy-day","partlycloudyday"),
    PARTLY_CLOUDY_NIGHT("partly-cloudy-night","partlycloudynight")
    ;

    private final String value;
    private final String icon;

    WeatherType(String value, String icon) {
        this.value = value;
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return value;
    }

    public static WeatherType getWeatherType(String weatherType) {

        if (CLEAR_DAY.value.equalsIgnoreCase(weatherType)) {
            return CLEAR_DAY;
        } else if (CLEAR_NIGHT.value.equalsIgnoreCase(weatherType)) {
            return CLEAR_NIGHT;
        }  else if (RAIN.value.equalsIgnoreCase(weatherType)) {
            return RAIN;
        }  else if (SNOW.value.equalsIgnoreCase(weatherType)) {
            return SNOW;
        }  else if (SLEET.value.equalsIgnoreCase(weatherType)) {
            return SLEET;
        } else if (WIND.value.equalsIgnoreCase(weatherType)) {
            return WIND;
        } else if (FOG.value.equalsIgnoreCase(weatherType)) {
            return FOG;
        } else if (CLOUDY.value.equalsIgnoreCase(weatherType)) {
            return CLOUDY;
        } else if (PARTLY_CLOUDY_DAY.value.equalsIgnoreCase(weatherType)) {
            return PARTLY_CLOUDY_DAY;
        } else if (PARTLY_CLOUDY_NIGHT.value.equalsIgnoreCase(weatherType)) {
            return PARTLY_CLOUDY_NIGHT;
        }

        //Default to CLEAR_DAY
        return CLEAR_DAY;
    }
}

