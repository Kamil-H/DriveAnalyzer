package kamilhalko.com.driveanalyzer.data.network;

import io.reactivex.Observable;
import kamilhalko.com.driveanalyzer.data.models.Trip;

public interface NetworkHelper {
    Observable<Long> synchronize(final Trip trip);
}
