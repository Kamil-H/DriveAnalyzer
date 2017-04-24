package kamilhalko.com.driveanalyzer.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.models.GearData;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.storage.StorageHelper;

@Singleton
public class DataManagerImpl implements DataManager {
    private PublishSubject<Trip> publishSubject = PublishSubject.create();
    private Trip trip;

    private final StorageHelper storageHelper;
    private final NetworkHelper networkHelper;

    @Inject
    public DataManagerImpl(StorageHelper storageHelper, NetworkHelper networkHelper) {
        this.storageHelper = storageHelper;
        this.networkHelper = networkHelper;
    }

    @Override
    public PublishSubject<Trip> getPublishSubject() {
        return publishSubject;
    }

    @Override
    public void setPublishSubject(PublishSubject<Trip> publishSubject) {
        this.publishSubject = publishSubject;
    }

    @Override
    public Trip getTrip() {
        return trip;
    }

    @Override
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void synchronize() {
        networkHelper.synchronize();
    }

    @Override
    public Observable<List<Trip>> getTrips() {
        return storageHelper.getTrips();
    }

    @Override
    public void save(Trip trip) {
        storageHelper.save(trip);
    }

    @Override
    public void save(GearData gearData) {
        storageHelper.save(gearData);
    }
}
