package kamilhalko.com.driveanalyzer.dependency_injection.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import kamilhalko.com.driveanalyzer.DriveAnalyzerApp;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.dependency_injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(DriveAnalyzerApp app);
    DataManager getDataManager();
    Context getContext();
}
