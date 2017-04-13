package kamilhalko.com.driveanalyzer.dependency_injection.module;

import android.app.Service;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;

import org.joda.time.DateTime;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gps;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.data.models.sensors.MagneticField;
import kamilhalko.com.driveanalyzer.data.models.sensors.Obd;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;
import kamilhalko.com.driveanalyzer.utils.AppConstants;
import kamilhalko.com.driveanalyzer.utils.DateUtils;

@Module
public class ServiceModule {

    private final Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    MotionSensorManager provideMotionSensorManager() {
        return new MotionSensorManager((SensorManager) service.getSystemService(Context.SENSOR_SERVICE));
    }

    @Provides
    GpsManager provideGpsManager() {
        return new GpsManager((LocationManager) service.getSystemService(Context.LOCATION_SERVICE));
    }

    @Provides
    Trip provideTrip() {
        return new Trip.Builder()
                .setTime(DateTime.now().toDateTimeISO().toString())
                .setDeviceId(AppConstants.getDeviceId(service))
                .setGpsList(new ArrayList<Gps>())
                .setObdList(new ArrayList<Obd>())
                .setMagneticFieldList(new ArrayList<MagneticField>())
                .setGyroscopeList(new ArrayList<Gyroscope>())
                .setAccelerometerList(new ArrayList<Accelerometer>())
                .setMillis(DateUtils.now())
                .createTrip();
    }
}
