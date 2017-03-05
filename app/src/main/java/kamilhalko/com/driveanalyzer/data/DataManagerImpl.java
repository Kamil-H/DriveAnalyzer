package kamilhalko.com.driveanalyzer.data;

import java.util.List;

import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.models.SensorData;

public class DataManagerImpl implements DataManager {
    private static DataManagerImpl instance = new DataManagerImpl();
    private PublishSubject<SensorData> publishSubject = PublishSubject.create();
    private List<SensorData> sensorDataList;

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
}
