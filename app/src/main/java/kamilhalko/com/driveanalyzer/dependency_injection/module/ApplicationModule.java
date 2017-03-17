package kamilhalko.com.driveanalyzer.dependency_injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.DataManagerImpl;
import kamilhalko.com.driveanalyzer.data.database.DbHelper;
import kamilhalko.com.driveanalyzer.data.database.DbHelperImpl;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelperImpl;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(DbHelperImpl dbHelper) {
        return dbHelper;
    }

    @Provides
    @Singleton
    NetworkHelper provideNetworkHelper(NetworkHelperImpl networkHelper) {
        return networkHelper;
    }
}