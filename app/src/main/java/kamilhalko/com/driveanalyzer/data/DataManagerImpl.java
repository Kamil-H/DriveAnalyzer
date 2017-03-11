package kamilhalko.com.driveanalyzer.data;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelperImpl;

public class DataManagerImpl implements DataManager {
    private static DataManagerImpl instance = new DataManagerImpl();
    private PublishSubject<Trip> publishSubject = PublishSubject.create();
    private Trip trip;
    private NetworkHelper networkHelper = NetworkHelperImpl.getInstance();

    public static DataManagerImpl getInstance() {
        return instance;
    }

    private DataManagerImpl() {}

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
    public Observable<Long> synchronize(Context context, Trip trip) {
        return networkHelper.synchronize(context, trip);
    }
}
