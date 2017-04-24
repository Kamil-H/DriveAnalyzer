package kamilhalko.com.driveanalyzer.data.models;

import java.util.List;

public class GearData {
    private long id;
    private long millis;
    private List<EngineData> engineDataList;

    public GearData(long millis, List<EngineData> engineDataList) {
        this.millis = millis;
        this.engineDataList = engineDataList;
    }

    public long getId() {
        return id;
    }

    public long getMillis() {
        return millis;
    }

    public List<EngineData> getEngineDataList() {
        return engineDataList;
    }

    public void setId(long id) {
        this.id = id;
    }
}
