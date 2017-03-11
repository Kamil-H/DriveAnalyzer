package kamilhalko.com.driveanalyzer.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.DataManagerImpl;
import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.data.models.factories.SensorDataFactory;
import kamilhalko.com.driveanalyzer.dependency_injection.Injector;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;
import kamilhalko.com.driveanalyzer.utils.ServiceUtil;

public class DriveAnalyzeService extends Service implements GpsManager.GpsLocationListener {
    private MotionSensorManager motionSensorManager;
    private GpsManager gpsManager;
    private List<SensorData> sensorDataList = new ArrayList<>();
    private DataManager dataManager;

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
        configureMotionSensorManager();
        configureGpsManager();
        dataManager = DataManagerImpl.getInstance();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void configureMotionSensorManager() {
        motionSensorManager = Injector.injectMotionSensorManager(this);
        motionSensorManager
                .addUpdatesForGyroscopeSensor()
                .addUpdatesForMagneticFieldSensor()
                .addUpdatesForAccelerometerSensor();
    }

    private void configureGpsManager() {
        gpsManager = Injector.injectGpsManager(this);
        gpsManager.requestLocations();
        gpsManager.setGpsLocationListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        SensorData sensorData = SensorDataFactory.create(location, motionSensorManager);
        sensorDataList.add(sensorData);
        notifyData(sensorData);
    }

    @Override
    public void onProviderDisabled() {
        stopService(this);
    }

    private void notifyData(SensorData sensorData) {
        dataManager.setSensorDataList(sensorDataList);
        dataManager.getPublishSubject().onNext(sensorData);
    }

    private void finish() {
        motionSensorManager.removeUpdates();
        gpsManager.removeUpdates();
        dataManager.setSensorDataList(null);
        dataManager.getPublishSubject().onComplete();
        dataManager.setPublishSubject(PublishSubject.<SensorData>create());
        saveData();
    }

    private void saveData() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
