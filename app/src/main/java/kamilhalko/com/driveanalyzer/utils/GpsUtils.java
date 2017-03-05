package kamilhalko.com.driveanalyzer.utils;

import android.content.Context;
import android.location.LocationManager;

public class GpsUtils {

    public static boolean isGpsActive(Context context) {
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
