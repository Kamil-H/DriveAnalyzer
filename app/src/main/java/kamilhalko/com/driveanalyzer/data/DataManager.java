package kamilhalko.com.driveanalyzer.data;

import kamilhalko.com.driveanalyzer.data.database.DbHelper;
import kamilhalko.com.driveanalyzer.data.network.NetworkHelper;
import kamilhalko.com.driveanalyzer.data.preferences.PreferencesHelper;

public interface DataManager extends DbHelper, NetworkHelper, PreferencesHelper {

}
