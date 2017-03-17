package kamilhalko.com.driveanalyzer.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.driveanalyzer.data.database.DbHelper;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;

@Singleton
public class DataManagerImpl implements DataManager {
    private PublishSubject<Trip> publishSubject = PublishSubject.create();
    private Trip trip;

    private final Context context;
    private final DbHelper dbHelper;
    private final NetworkHelper networkHelper;

    @Inject
    public DataManagerImpl(Context context, DbHelper dbHelper, NetworkHelper networkHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
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
    public Observable<Long> synchronize(Trip trip) {
        return networkHelper.synchronize(trip);
    }
}
