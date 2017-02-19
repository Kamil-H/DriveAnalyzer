package kamilhalko.com.driveanalyzer.presenters;

import kamilhalko.com.driveanalyzer.views.MvpView;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}
