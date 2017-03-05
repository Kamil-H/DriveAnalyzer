package kamilhalko.com.driveanalyzer.utils;

import android.content.Context;
import android.provider.Settings;

public class AppConstants {

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
