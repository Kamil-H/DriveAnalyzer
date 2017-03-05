package kamilhalko.com.driveanalyzer.utils;

import android.app.ActivityManager;
import android.content.Context;

public class ServiceUtil {

    public static boolean isServiceRunning(Context context, Class className) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (className.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
