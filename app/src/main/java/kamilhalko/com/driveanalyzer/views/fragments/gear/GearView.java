package kamilhalko.com.driveanalyzer.views.fragments.gear;

import kamilhalko.com.driveanalyzer.views.MvpView;

public interface GearView extends MvpView {
    void showStartLayout();
    void showGearLayout();
    void setUpButtons();
    void onSaved();
}
