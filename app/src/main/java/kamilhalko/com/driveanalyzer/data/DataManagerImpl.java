package kamilhalko.com.driveanalyzer.data;

public class DataManagerImpl implements DataManager {
    private static DataManagerImpl instance = new DataManagerImpl();

    public static DataManagerImpl getInstance() {
        return instance;
    }

    private DataManagerImpl() {}
}
