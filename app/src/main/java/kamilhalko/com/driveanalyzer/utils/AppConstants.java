package kamilhalko.com.driveanalyzer.utils;

import android.content.Context;
import android.provider.Settings;

public class AppConstants {
    public static final String SERVER_ADDRESS = "http://104.238.177.72:8000/";
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
