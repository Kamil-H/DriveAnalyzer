package kamilhalko.com.driveanalyzer.views.activities;

import kamilhalko.com.driveanalyzer.views.MvpView;

public interface MainView extends MvpView {
    void setUpView();
    void setUpToolbar();
    void setUpNavigationBottomView();
    void startDriveAnalyzeService();
    boolean isGpsEnabled();
    void startService();
    void enableBluetooth();
}
