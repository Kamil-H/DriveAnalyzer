package kamilhalko.com.driveanalyzer.managers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MotionSensorManager implements SensorEventListener {
    private SensorManager sensorManager;
    private float accelerometer = 0, gyroscope = 0, magneticField = 0;

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
        float value = (float) Math.sqrt(x*x + y*y + z*z);

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometer = value;
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            gyroscope = value;
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magneticField = value;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public void removeUpdates() {
        sensorManager.unregisterListener(this);
    }

    public float getAccelerometer() {
        return accelerometer;
    }

    public float getGyroscope() {
        return gyroscope;
    }

    public float getMagneticField() {
        return magneticField;
    }
}
