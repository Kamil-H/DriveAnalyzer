package kamilhalko.com.driveanalyzer.views.fragments.recording;

import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.views.MvpView;

public interface RecordingView extends MvpView {
    void updateData(Trip trip);
    void onRecordingFinished();
    void showStartLayout();
    void showRecordingLayout();
    void setUpButtons();
    void setUpView();
}
