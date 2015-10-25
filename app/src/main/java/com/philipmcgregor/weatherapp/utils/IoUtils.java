package com.philipmcgregor.weatherapp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Utility class for IO
 */
public class IoUtils {
    private static String TAG = IoUtils.class.getSimpleName();

    public static String readStreamConvertToString(InputStream in) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "readStreamConvertToString ",e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "readStreamConvertToString ", e);
                }
            }
        }
        return response.toString();
    }
}
