package kamilhalko.com.driveanalyzer.dependency_injection.module;

import android.app.Service;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;

import org.joda.time.DateTime;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;
import kamilhalko.com.driveanalyzer.utils.AppConstants;

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
        return new Trip(DateTime.now().toDateTimeISO().toString(), AppConstants.getDeviceId(service), new ArrayList<SensorData>());
    }
}
