package kamilhalko.com.driveanalyzer;

import android.app.Application;

import kamilhalko.com.driveanalyzer.dependency_injection.component.ApplicationComponent;
import kamilhalko.com.driveanalyzer.dependency_injection.component.DaggerApplicationComponent;
import kamilhalko.com.driveanalyzer.dependency_injection.module.ApplicationModule;

public class DriveAnalyzerApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
