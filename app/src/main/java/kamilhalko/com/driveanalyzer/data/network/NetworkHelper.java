package kamilhalko.com.driveanalyzer.data.network;

import android.content.Context;

import io.reactivex.Observable;
import kamilhalko.com.driveanalyzer.data.models.Trip;

public interface NetworkHelper {
    Observable<Long> synchronize(final Context context, final Trip trip);
}
