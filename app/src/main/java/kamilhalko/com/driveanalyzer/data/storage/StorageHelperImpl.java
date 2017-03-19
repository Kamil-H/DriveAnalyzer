package kamilhalko.com.driveanalyzer.data.storage;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import kamilhalko.com.driveanalyzer.data.models.Trip;

@Singleton
public class StorageHelperImpl implements StorageHelper {
    private FileHelper fileHelper;

    @Inject
    public StorageHelperImpl(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    @Override
    public Observable<List<Trip>> getTrips() {
        return Observable.fromCallable(new Callable<List<Trip>>() {
            @Override
            public List<Trip> call() throws Exception {
                return fileHelper.getTrips();
            }
        });
    }

    @Override
    public void saveTrip(Trip trip) {
        fileHelper.saveTrip(trip);
    }
}
