package kamilhalko.com.driveanalyzer.data.models.factories;

import android.location.Location;

import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;

public class SensorDataFactory {

    public static SensorData create(Location location, MotionSensorManager motionSensorManager) {
        SensorData sensorData = new SensorData();
        sensorData.setAccelerometer(motionSensorManager.getAccelerometer());
        sensorData.setGyroscope(motionSensorManager.getGyroscope());
        sensorData.setMagneticField(motionSensorManager.getMagneticField());
        sensorData.setLatitude(location.getLatitude());
        sensorData.setLongitude(location.getLongitude());
        sensorData.setSpeed(location.getSpeed());
        sensorData.setTime(location.getTime());

        return sensorData;
    }
}