package kamilhalko.com.driveanalyzer.data;

import java.util.List;

import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.database.DbHelper;
import kamilhalko.com.driveanalyzer.data.models.SensorData;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.preferences.PreferencesHelper;

public interface DataManager extends DbHelper, NetworkHelper, PreferencesHelper {
    PublishSubject<SensorData> getPublishSubject();
    void setPublishSubject(PublishSubject<SensorData> publishSubject);
    List<SensorData> getSensorDataList();
    void setSensorDataList(List<SensorData> sensorDataList);
}
