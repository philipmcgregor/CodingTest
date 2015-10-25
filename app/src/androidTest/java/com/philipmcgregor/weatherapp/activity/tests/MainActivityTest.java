package com.philipmcgregor.weatherapp.activity.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;


import com.philipmcgregor.weatherapp.R;
import com.philipmcgregor.weatherapp.activity.MainActivity;

/**
 *
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private TextView weatherServiceResponseTitle,forcastTitle;


    public MainActivityTest(){
        super(MainActivity.class);
    }


    public MainActivityTest(Class<MainActivityTest> activityClass) {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        weatherServiceResponseTitle = (TextView) mainActivity.findViewById(R.id.weatherServiceResposneTitleTextView);
        forcastTitle = (TextView) mainActivity.findViewById(R.id.forcastTitleTextView);
    }

    public void testPreconditions() {
        assertNotNull("mainActivity is null", mainActivity);
        assertNotNull("weatherServiceResponseTitle is null", weatherServiceResponseTitle);
        assertNotNull("forcastTitle is null", forcastTitle);
    }

    public void testWeatherServiceResponseTitle_labelText() {
        final String expected =
                mainActivity.getString(R.string.callingWeatherService);
        final String actual = weatherServiceResponseTitle.getText().toString();
        assertEquals(expected, actual);
    }

    public void testForcastTitle_labelText() {
        final String expected =
                mainActivity.getString(R.string.forcastSummary);
        final String actual = forcastTitle.getText().toString();
        assertEquals(expected, actual);
    }
}
