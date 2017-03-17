package kamilhalko.com.driveanalyzer.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import kamilhalko.com.driveanalyzer.DriveAnalyzerApp;
import kamilhalko.com.driveanalyzer.dependency_injection.component.DaggerServiceComponent;
import kamilhalko.com.driveanalyzer.dependency_injection.component.ServiceComponent;
import kamilhalko.com.driveanalyzer.dependency_injection.module.ServiceModule;

public class BaseService extends Service {
    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .applicationComponent(((DriveAnalyzerApp) getApplication()).getApplicationComponent())
                .build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
