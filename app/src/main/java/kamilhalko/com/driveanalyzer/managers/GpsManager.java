package kamilhalko.com.driveanalyzer.managers;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class GpsManager implements LocationListener {
    private LocationManager locationManager;
    private GpsLocationListener gpsLocationListener;
    Handler handler;

    public GpsManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public void requestLocations() {
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        test();
    }

    private void test() {
        handler = new Handler();
        handler.postDelayed(updateTimeThread ,0);
    }

    private Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            Location location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(56.1d);
            location.setLongitude(16.2d);
            location.setSpeed(15f);
            location.setTime(SystemClock.currentThreadTimeMillis());
            notifyData(location);
            handler.postDelayed(this, 1000);
        }
    };

    public void setGpsLocationListener(GpsLocationListener gpsLocationListener) {
        this.gpsLocationListener = gpsLocationListener;
    }

    private void notifyData(Location location) {
        if (gpsLocationListener != null) {
            gpsLocationListener.onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        notifyData(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {
        if (gpsLocationListener != null) {
            gpsLocationListener.onProviderDisabled();
        }
    }

    public void removeUpdates() {
        locationManager.removeUpdates(this);
        gpsLocationListener = null;
    }

    public interface GpsLocationListener {
        void onLocationChanged(Location location);
        void onProviderDisabled();
    }
}
