package kamilhalko.com.driveanalyzer.data.models;

import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gps;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.data.models.sensors.MagneticField;
import kamilhalko.com.driveanalyzer.data.models.sensors.Obd;

public class SensorData {
    private Accelerometer accelerometer;
    private Obd obd;
    private Gps gps;
    private Gyroscope gyroscope;
    private MagneticField magneticField;

    public SensorData(Accelerometer accelerometer, Obd obd, Gps gps, Gyroscope gyroscope, MagneticField magneticField) {
        this.accelerometer = accelerometer;
        this.obd = obd;
        this.gps = gps;
        this.gyroscope = gyroscope;
        this.magneticField = magneticField;
    }

    public Accelerometer getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(Accelerometer accelerometer) {
        this.accelerometer = accelerometer;
    }

    public Obd getObd() {
        return obd;
    }

    public void setObd(Obd obd) {
        this.obd = obd;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public Gyroscope getGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(Gyroscope gyroscope) {
        this.gyroscope = gyroscope;
    }

    public MagneticField getMagneticField() {
        return magneticField;
    }

    public void setMagneticField(MagneticField magneticField) {
        this.magneticField = magneticField;
    }

    public static class Builder {
        private Accelerometer accelerometer;
        private Obd obd;
        private Gps gps;
        private Gyroscope gyroscope;
        private MagneticField magneticField;

        public Builder setAccelerometer(Accelerometer accelerometer) {
            this.accelerometer = accelerometer;
            return this;
        }

        public Builder setObd(Obd obd) {
            this.obd = obd;
            return this;
        }

        public Builder setGps(Gps gps) {
            this.gps = gps;
            return this;
        }

        public Builder setGyroscope(Gyroscope gyroscope) {
            this.gyroscope = gyroscope;
            return this;
        }

        public Builder setMagneticField(MagneticField magneticField) {
            this.magneticField = magneticField;
            return this;
        }

        public SensorData build() {
            return new SensorData(accelerometer, obd, gps, gyroscope, magneticField);
        }
    }
}
