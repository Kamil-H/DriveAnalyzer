package kamilhalko.com.driveanalyzer.data.models;

import java.util.List;

import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gps;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.data.models.sensors.MagneticField;
import kamilhalko.com.driveanalyzer.data.models.sensors.Obd;

public class Trip {
    private long id;
    private String time;
    private String deviceId;
    private List<Gps> gpsList;
    private List<Obd> obdList;
    private List<MagneticField> magneticFieldList;
    private List<Gyroscope> gyroscopeList;
    private List<Accelerometer> accelerometerList;
    private long millis;
    private double distance;
    private double avgSpeed;

    public Trip(String time, String deviceId, List<Gps> gpsList, List<Obd> obdList, List<MagneticField> magneticFieldList,
                List<Gyroscope> gyroscopeList, List<Accelerometer> accelerometerList, long millis) {
        this.time = time;
        this.deviceId = deviceId;
        this.gpsList = gpsList;
        this.obdList = obdList;
        this.magneticFieldList = magneticFieldList;
        this.gyroscopeList = gyroscopeList;
        this.accelerometerList = accelerometerList;
        this.millis = millis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Gps> getGpsList() {
        return gpsList;
    }

    public void setGpsList(List<Gps> gpsList) {
        this.gpsList = gpsList;
    }

    public List<Obd> getObdList() {
        return obdList;
    }

    public void setObdList(List<Obd> obdList) {
        this.obdList = obdList;
    }

    public List<MagneticField> getMagneticFieldList() {
        return magneticFieldList;
    }

    public void setMagneticFieldList(List<MagneticField> magneticFieldList) {
        this.magneticFieldList = magneticFieldList;
    }

    public List<Gyroscope> getGyroscopeList() {
        return gyroscopeList;
    }

    public void setGyroscopeList(List<Gyroscope> gyroscopeList) {
        this.gyroscopeList = gyroscopeList;
    }

    public List<Accelerometer> getAccelerometerList() {
        return accelerometerList;
    }

    public void setAccelerometerList(List<Accelerometer> accelerometerList) {
        this.accelerometerList = accelerometerList;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public static class Builder {
        private String time;
        private String deviceId;
        private List<Gps> gpsList;
        private List<Obd> obdList;
        private List<MagneticField> magneticFieldList;
        private List<Gyroscope> gyroscopeList;
        private List<Accelerometer> accelerometerList;
        private long millis;

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder setGpsList(List<Gps> gpsList) {
            this.gpsList = gpsList;
            return this;
        }

        public Builder setObdList(List<Obd> obdList) {
            this.obdList = obdList;
            return this;
        }

        public Builder setMagneticFieldList(List<MagneticField> magneticFieldList) {
            this.magneticFieldList = magneticFieldList;
            return this;
        }

        public Builder setGyroscopeList(List<Gyroscope> gyroscopeList) {
            this.gyroscopeList = gyroscopeList;
            return this;
        }

        public Builder setAccelerometerList(List<Accelerometer> accelerometerList) {
            this.accelerometerList = accelerometerList;
            return this;
        }

        public Builder setMillis(long millis) {
            this.millis = millis;
            return this;
        }

        public Trip createTrip() {
            return new Trip(time, deviceId, gpsList, obdList, magneticFieldList, gyroscopeList, accelerometerList, millis);
        }
    }
}
