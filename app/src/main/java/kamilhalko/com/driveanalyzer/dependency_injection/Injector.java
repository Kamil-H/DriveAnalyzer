package kamilhalko.com.driveanalyzer.dependency_injection;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManagerImpl;
import kamilhalko.com.driveanalyzer.presenters.activities.MainPresenter;
import kamilhalko.com.driveanalyzer.views.activities.MainActivity;

public class Injector {

    public static MainPresenter injectPresenter(MainActivity mainActivity) {
        return new MainPresenter(DataManagerImpl.getInstance(), new CompositeDisposable());
    }
}
