package kamilhalko.com.driveanalyzer.data.models;

public class EngineData {
    private int gearPosition;
    private double rpm;
    private double speed;
    private double ration;
    private long millis;

    public EngineData(int gearPosition, double rpm, double speed, long millis) {
        this.gearPosition = gearPosition;
        this.rpm = rpm;
        this.speed = speed;
        this.ration = speed != 0 ? rpm/speed : 0;
        this.millis = millis;
    }
}
