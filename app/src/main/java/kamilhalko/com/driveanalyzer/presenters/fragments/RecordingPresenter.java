package kamilhalko.com.driveanalyzer.presenters.fragments;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.presenters.BasePresenter;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingView;

public class RecordingPresenter<V extends RecordingView> extends BasePresenter<V> {
    private List<SensorData> sensorDataList;

    public RecordingPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void onStartRecording() {
        getSensorDataList();
        getCompositeDisposable().clear();
        getCompositeDisposable().add(getDataManager().getPublishSubject()
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        getMvpView().onRecordingFinished();
                    }
                })
                .subscribe(new Consumer<SensorData>() {
                    @Override
                    public void accept(SensorData sensorData) throws Exception {
                        if (sensorDataList != null) {
                            sensorDataList.add(sensorData);
                        }
                        getMvpView().updateData(sensorData);
                    }
                }));
    }

    private void getSensorDataList() {
        if (getDataManager().getSensorDataList() != null) {
            sensorDataList = getDataManager().getSensorDataList();
            getMvpView().showRecordingLayout();
        }
    }
}
