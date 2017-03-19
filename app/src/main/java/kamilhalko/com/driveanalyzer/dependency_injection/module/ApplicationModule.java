package kamilhalko.com.driveanalyzer.dependency_injection.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kamilhalko.com.driveanalyzer.data.DataManager;
import kamilhalko.com.driveanalyzer.data.DataManagerImpl;
import kamilhalko.com.driveanalyzer.data.storage.FileHelper;
import kamilhalko.com.driveanalyzer.data.storage.StorageHelper;
import kamilhalko.com.driveanalyzer.data.storage.StorageHelperImpl;
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
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }

    @Provides
    @Singleton
    StorageHelper provideStorageHelper(StorageHelperImpl storageHelper) {
        return storageHelper;
    }

    @Provides
    @Singleton
    NetworkHelper provideNetworkHelper(NetworkHelperImpl networkHelper) {
        return networkHelper;
    }

    @Provides
    @Singleton
    FileHelper provideFileHelper(Context context, Gson gson) {
        return new FileHelper(context, gson);
    }
}