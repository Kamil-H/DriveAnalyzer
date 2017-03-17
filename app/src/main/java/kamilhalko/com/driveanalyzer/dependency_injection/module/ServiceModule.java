package kamilhalko.com.driveanalyzer.dependency_injection.module;

import android.app.Service;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;

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
    GpsManager injectGpsManager() {
        return new GpsManager((LocationManager) service.getSystemService(Context.LOCATION_SERVICE));
    }
}
