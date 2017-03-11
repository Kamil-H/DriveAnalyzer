package kamilhalko.com.driveanalyzer.presenters.fragments;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.presenters.BasePresenter;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingView;

public class RecordingPresenter<V extends RecordingView> extends BasePresenter<V> {

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
                .subscribe(new Consumer<Trip>() {
                    @Override
                    public void accept(Trip trip) throws Exception {
                        if (trip.getSensorDataList() != null) {
                            getMvpView().updateData(trip);
                        }
                    }
                }));
    }

    private void getSensorDataList() {
        if (getDataManager().getTrip() != null) {
            getMvpView().showRecordingLayout();
        }
    }
}
