package kamilhalko.com.driveanalyzer.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.DataManagerImpl;
import kamilhalko.com.driveanalyzer.data.models.Trip;

public class SyncService extends Service {
    private DataManager dataManager;

    public static void startService(Context context) {
        context.startService(new Intent(context, SyncService.class));
    }

    public static void stopService(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataManager = DataManagerImpl.getInstance();
    }

    private void synchronize() {
        dataManager.synchronize(this, new Trip())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong != null) {

                        }
                    }
                });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
