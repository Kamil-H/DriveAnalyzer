package kamilhalko.com.driveanalyzer.managers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.data.models.sensors.MagneticField;
import kamilhalko.com.driveanalyzer.utils.DateUtils;

public class MotionSensorManager implements SensorEventListener {
    private SensorManager sensorManager;
    private SensorValueFetched sensorValueFetched;

    public MotionSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public MotionSensorManager addUpdatesForAccelerometerSensor() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, 0);
        return this;
    }

    public MotionSensorManager addUpdatesForGyroscopeSensor() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensor, 0);
        return this;
    }

    public MotionSensorManager addUpdatesForMagneticFieldSensor() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this, sensor, 0);
        return this;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values.clone();
        float x = values[0];
        float y = values[1];
        float z = values[2];

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            sensorValueFetched.onAccelerometer(new Accelerometer(x, y, z, DateUtils.now()));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            sensorValueFetched.onGyroscope(new Gyroscope(x, y, z, DateUtils.now()));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            sensorValueFetched.onMagneticField(new MagneticField(x, y, z, DateUtils.now()));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public void removeUpdates() {
        sensorManager.unregisterListener(this);
    }

    public void setSensorValueFetched(SensorValueFetched sensorValueFetched) {
        if (sensorValueFetched != null) {
            this.sensorValueFetched = sensorValueFetched;
        }
    }

    public interface SensorValueFetched {
        void onAccelerometer(Accelerometer accelerometer);
        void onGyroscope(Gyroscope gyroscope);
        void onMagneticField(MagneticField magneticField);
    }
}
