package kamilhalko.com.driveanalyzer.data.storage;

import java.util.List;
import io.reactivex.Observable;

import kamilhalko.com.driveanalyzer.data.models.Trip;

public interface StorageHelper {
    Observable<List<Trip>> getTrips();
    void saveTrip(Trip trip);
}
