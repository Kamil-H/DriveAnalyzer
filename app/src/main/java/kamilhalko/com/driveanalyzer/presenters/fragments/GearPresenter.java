package kamilhalko.com.driveanalyzer.presenters.fragments;

import android.bluetooth.BluetoothSocket;
import android.content.Context;

import com.github.pires.obd.exceptions.UnableToConnectException;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.models.EngineData;
import kamilhalko.com.driveanalyzer.data.models.GearData;
import kamilhalko.com.driveanalyzer.presenters.BasePresenter;
import kamilhalko.com.driveanalyzer.services.BluetoothConnector;
import kamilhalko.com.driveanalyzer.services.ObdService;
import kamilhalko.com.driveanalyzer.utils.DateUtils;
import kamilhalko.com.driveanalyzer.views.fragments.gear.GearView;

public class GearPresenter<V extends GearView> extends BasePresenter<V> implements BluetoothConnector.DeviceConnectionCallback {
    private ObdService obdService;

    @Inject
    public GearPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void onStart(Context context) {
        BluetoothConnector.connect(context, this);
    }

    public void onStop() {
        getMvpView().showStartLayout();
        if (obdService != null) {
            getDataManager().save(obdService.getGearData());
            getDataManager().synchronize();
            try {
                obdService.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
            obdService = null;
        }
    }

    public void onReadButtonClicked(int gearPosition) {
        if (obdService != null) {
            try {
                obdService.readData(gearPosition);
                getMvpView().onSaved();
            } catch (IOException | InterruptedException | UnableToConnectException e) {
                e.printStackTrace();
                getMvpView().onError("FAILURE");
            }
        }
    }

    public void undo() {
        if (obdService != null) {
            obdService.removeLast();
        }
    }

    @Override
    public void onConnected(BluetoothSocket bluetoothSocket) {
        obdService = new ObdService(bluetoothSocket, new GearData(DateUtils.now(), new ArrayList<EngineData>()));
        getMvpView().showGearLayout();
    }

    @Override
    public void onConnectionFailed(String message) {
        getMvpView().onError(message);
    }
}
