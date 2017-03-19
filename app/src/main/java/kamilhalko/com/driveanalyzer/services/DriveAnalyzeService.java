package kamilhalko.com.driveanalyzer.services;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gps;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;
import kamilhalko.com.driveanalyzer.utils.ServiceUtil;

public class DriveAnalyzeService extends BaseService implements GpsManager.GpsLocationListener {
    @Inject DataManager dataManager;
    @Inject MotionSensorManager motionSensorManager;
    @Inject GpsManager gpsManager;
    @Inject Trip trip;

    private List<SensorData> sensorDataList = new ArrayList<>();

    public static void startService(Context context) {
        context.startService(new Intent(context, DriveAnalyzeService.class));
    }

    public static void stopService(Context context) {
        context.stopService(new Intent(context, DriveAnalyzeService.class));
    }

    public static boolean isRunning(Context context) {
        return ServiceUtil.isServiceRunning(context, DriveAnalyzeService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getServiceComponent().inject(this);
        configureMotionSensorManager();
        configureGpsManager();
        dataManager.getPublishSubject().onNext(trip);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void configureMotionSensorManager() {
        motionSensorManager
                .addUpdatesForGyroscopeSensor()
                .addUpdatesForMagneticFieldSensor()
                .addUpdatesForAccelerometerSensor();
    }

    private void configureGpsManager() {
        gpsManager.requestLocations();
        gpsManager.setGpsLocationListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        SensorData sensorData = new SensorData.Builder()
                .setAccelerometer(motionSensorManager.getAccelerometer())
                .setGyroscope(motionSensorManager.getGyroscope())
                .setMagneticField(motionSensorManager.getMagneticField())
                .setGps(new Gps(location))
                .build();
        sensorDataList.add(sensorData);
        trip.setSensorDataList(sensorDataList);
        notifyData(trip);
    }

    @Override
    public void onProviderDisabled() {
        stopService(this);
    }

    private void notifyData(Trip trip) {
        dataManager.getPublishSubject().onNext(trip);
    }

    private void finish() {
        motionSensorManager.removeUpdates();
        gpsManager.removeUpdates();
        dataManager.setTrip(null);
        dataManager.getPublishSubject().onComplete();
        dataManager.setPublishSubject(PublishSubject.<Trip>create());
        saveData();
    }

    private void saveData() {
        dataManager.saveTrip(trip);
        dataManager.synchronize();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
