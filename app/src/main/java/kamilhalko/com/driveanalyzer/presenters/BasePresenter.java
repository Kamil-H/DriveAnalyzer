package kamilhalko.com.driveanalyzer.presenters;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.views.MvpView;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private final DataManager dataManager;
    private final CompositeDisposable compositeDisposable;
    private V mvpView;

    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mvpView = null;
    }

    protected V getMvpView() {
        return mvpView;
    }

    protected DataManager getDataManager() {
        return dataManager;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
