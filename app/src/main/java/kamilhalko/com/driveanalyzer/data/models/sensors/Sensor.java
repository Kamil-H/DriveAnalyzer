package kamilhalko.com.driveanalyzer.data.models.sensors;

public abstract class Sensor {
    protected float x;
    protected float y;
    protected float z;
    protected float acceleration;
    protected long time;

    protected Sensor(float x, float y, float z, long time) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.acceleration = (float) Math.sqrt(x*x + y*y + z*z);
        this.time = time;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
