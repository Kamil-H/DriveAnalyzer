package kamilhalko.com.driveanalyzer.presenters.activities;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.presenters.BasePresenter;
import kamilhalko.com.driveanalyzer.views.MvpView;

public class MainPresenter<V extends MvpView> extends BasePresenter<V> {

    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
