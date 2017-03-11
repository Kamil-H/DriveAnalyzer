package kamilhalko.com.driveanalyzer.data;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelperImpl;

public class DataManagerImpl implements DataManager {
    private static DataManagerImpl instance = new DataManagerImpl();
    private PublishSubject<SensorData> publishSubject = PublishSubject.create();
    private List<SensorData> sensorDataList;
    private NetworkHelper networkHelper = NetworkHelperImpl.getInstance();

    public static DataManagerImpl getInstance() {
        return instance;
    }

    private DataManagerImpl() {}

    @Override
    public PublishSubject<SensorData> getPublishSubject() {
        return publishSubject;
    }

    @Override
    public void setPublishSubject(PublishSubject<SensorData> publishSubject) {
        this.publishSubject = publishSubject;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    @Override
    public Observable<Long> synchronize(Context context, Trip trip) {
        return networkHelper.synchronize(context, trip);
    }
}
