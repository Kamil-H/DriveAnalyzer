package kamilhalko.com.driveanalyzer.views.fragments.recording;

import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.views.MvpView;

public interface RecordingView extends MvpView {
    void updateData(SensorData sensorData);
    void onRecordingFinished();
    void showStartLayout();
    void showRecordingLayout();
    void setUpButtons();
    void setUpView();
}
