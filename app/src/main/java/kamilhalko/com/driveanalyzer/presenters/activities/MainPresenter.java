package kamilhalko.com.driveanalyzer.presenters.activities;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.presenters.BasePresenter;
import kamilhalko.com.driveanalyzer.views.activities.MainView;

public class MainPresenter<V extends MainView> extends BasePresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        //getDataManager().synchronize();
    }
}
