package kamilhalko.com.driveanalyzer.data.storage;

import java.util.List;
import io.reactivex.Observable;

import kamilhalko.com.driveanalyzer.data.models.GearData;
import kamilhalko.com.driveanalyzer.data.models.Trip;

public interface StorageHelper {
    Observable<List<Trip>> getTrips();
    void save(Trip trip);
    void save(GearData gearData);
}
