package kamilhalko.com.driveanalyzer.data;

import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.storage.StorageHelper;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.preferences.PreferencesHelper;

public interface DataManager extends StorageHelper, NetworkHelper, PreferencesHelper {
    PublishSubject<Trip> getPublishSubject();
    void setPublishSubject(PublishSubject<Trip> publishSubject);
    Trip getTrip();
    void setTrip(Trip trip);
}
