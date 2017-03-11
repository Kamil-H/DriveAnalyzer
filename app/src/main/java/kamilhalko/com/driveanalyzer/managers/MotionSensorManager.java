package kamilhalko.com.driveanalyzer.managers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.data.models.sensors.MagneticField;

public class MotionSensorManager implements SensorEventListener {
    private SensorManager sensorManager;
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private MagneticField magneticField;

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
            accelerometer = new Accelerometer(x, y, z);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            gyroscope = new Gyroscope(x, y, z);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magneticField = new MagneticField(x, y, z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public void removeUpdates() {
        sensorManager.unregisterListener(this);
    }

    public Accelerometer getAccelerometer() {
        return accelerometer;
    }

    public Gyroscope getGyroscope() {
        return gyroscope;
    }

    public MagneticField getMagneticField() {
        return magneticField;
    }
}
