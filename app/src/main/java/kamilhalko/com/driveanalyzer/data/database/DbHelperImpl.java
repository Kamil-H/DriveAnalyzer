package kamilhalko.com.driveanalyzer.data.database;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbHelperImpl implements DbHelper {
    private Context context;

    @Inject
    public DbHelperImpl(Context context) {
        this.context = context;
    }
}
