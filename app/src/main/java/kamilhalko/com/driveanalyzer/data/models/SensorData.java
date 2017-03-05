package kamilhalko.com.driveanalyzer.data.models;

public class SensorData {
    private double latitude;
    private double longitude;
    private float gyroscope;
    private float accelerometer;
    private float magneticField;
    private float speed;
    private long time;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(float gyroscope) {
        this.gyroscope = gyroscope;
    }

    public float getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(float accelerometer) {
        this.accelerometer = accelerometer;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getMagneticField() {
        return magneticField;
    }

    public void setMagneticField(float magneticField) {
        this.magneticField = magneticField;
    }
}
