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
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gps;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.data.models.sensors.MagneticField;
import kamilhalko.com.driveanalyzer.data.models.sensors.Obd;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;
import kamilhalko.com.driveanalyzer.utils.ServiceUtil;

public class DriveAnalyzeService extends BaseService implements GpsManager.GpsLocationListener, MotionSensorManager.SensorValueFetched {
    @Inject DataManager dataManager;
    @Inject MotionSensorManager motionSensorManager;
    @Inject GpsManager gpsManager;
    @Inject Trip trip;

    private List<Gps> gpsList = new ArrayList<>();
    private List<Obd> obdList = new ArrayList<>();
    private List<Accelerometer> accelerometerList = new ArrayList<>();
    private List<MagneticField> magneticFieldList = new ArrayList<>();
    private List<Gyroscope> gyroscopeList = new ArrayList<>();

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
        motionSensorManager.setSensorValueFetched(this);
    }

    private void configureGpsManager() {
        gpsManager.requestLocations();
        gpsManager.setGpsLocationListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        gpsList.add(new Gps(location));
        trip.setGpsList(gpsList);
        notifyData(trip);
    }

    @Override
    public void onAccelerometer(Accelerometer accelerometer) {
        accelerometerList.add(accelerometer);
        trip.setAccelerometerList(accelerometerList);
        notifyData(trip);
    }

    @Override
    public void onGyroscope(Gyroscope gyroscope) {
        gyroscopeList.add(gyroscope);
        trip.setGyroscopeList(gyroscopeList);
        notifyData(trip);
    }

    @Override
    public void onMagneticField(MagneticField magneticField) {
        magneticFieldList.add(magneticField);
        trip.setMagneticFieldList(magneticFieldList);
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
