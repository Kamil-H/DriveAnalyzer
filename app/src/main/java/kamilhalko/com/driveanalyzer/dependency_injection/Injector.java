package kamilhalko.com.driveanalyzer.dependency_injection;

import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManagerImpl;
import kamilhalko.com.driveanalyzer.managers.GpsManager;
import kamilhalko.com.driveanalyzer.managers.MotionSensorManager;
import kamilhalko.com.driveanalyzer.presenters.activities.MainPresenter;
import kamilhalko.com.driveanalyzer.presenters.fragments.RecordingPresenter;
import kamilhalko.com.driveanalyzer.views.activities.MainActivity;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingFragment;

public class Injector {

    public static MainPresenter injectPresenter(MainActivity mainActivity) {
        return new MainPresenter(DataManagerImpl.getInstance(), new CompositeDisposable());
    }

    public static RecordingPresenter injectPresenter(RecordingFragment recordingFragment) {
        return new RecordingPresenter(DataManagerImpl.getInstance(), new CompositeDisposable());
    }

    public static MotionSensorManager injectMotionSensorManager(Context context) {
        return new MotionSensorManager(getSensorManager(context));
    }

    private static SensorManager getSensorManager(Context context) {
        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public static GpsManager injectGpsManager(Context context) {
        return new GpsManager(getLocationManager(context));
    }

    private static LocationManager getLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
