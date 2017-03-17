package kamilhalko.com.driveanalyzer.dependency_injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.dependency_injection.PerActivity;
import kamilhalko.com.driveanalyzer.presenters.activities.MainPresenter;
import kamilhalko.com.driveanalyzer.presenters.fragments.RecordingPresenter;
import kamilhalko.com.driveanalyzer.views.activities.MainView;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingView;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    MainPresenter<MainView> provideMainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new MainPresenter<>(dataManager, compositeDisposable);
    }

    @Provides
    @PerActivity
    RecordingPresenter<RecordingView> provideRecordingPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new RecordingPresenter<>(dataManager, compositeDisposable);
    }
}
