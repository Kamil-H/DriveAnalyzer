package kamilhalko.com.driveanalyzer.dependency_injection.component;

import dagger.Component;
import kamilhalko.com.driveanalyzer.dependency_injection.PerActivity;
import kamilhalko.com.driveanalyzer.dependency_injection.module.ActivityModule;
import kamilhalko.com.driveanalyzer.views.activities.MainActivity;
import kamilhalko.com.driveanalyzer.views.fragments.gear.GearFragment;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingFragment;
import kamilhalko.com.driveanalyzer.views.fragments.settings.SettingsFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(RecordingFragment fragment);
    void inject(GearFragment fragment);
    void inject(SettingsFragment fragment);
}
