package kamilhalko.com.driveanalyzer.data.models;

import java.util.List;

public class Trip {
    private long id;
    private String time;
    private String deviceId;
    private List<SensorData> sensorDataList;
    private double distance;
    private double avgSpeed;

    public Trip(String time, String deviceId, List<SensorData> sensorDataList) {
        this.time = time;
        this.deviceId = deviceId;
        this.sensorDataList = sensorDataList;
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

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
