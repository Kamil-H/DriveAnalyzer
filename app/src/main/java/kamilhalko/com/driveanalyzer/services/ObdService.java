package kamilhalko.com.driveanalyzer.services;

import android.bluetooth.BluetoothSocket;

import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.engine.RPMCommand;

import java.io.IOException;

import kamilhalko.com.driveanalyzer.data.models.EngineData;
import kamilhalko.com.driveanalyzer.data.models.GearData;
import kamilhalko.com.driveanalyzer.utils.DateUtils;

public class ObdService {
    private BluetoothSocket socket;

    private GearData gearData;

    public ObdService(BluetoothSocket socket, GearData gearData) {
        this.socket = socket;
        this.gearData = gearData;
    }

    public void readData(int gearPosition) throws IOException, InterruptedException {
        RPMCommand rpmCommand = new RPMCommand();
        double rpm = readRpm(rpmCommand);

        SpeedCommand speedCommand = new SpeedCommand();
        double speed = readSpeed(speedCommand);

        EngineData engineData = new EngineData(gearPosition, rpm, speed, DateUtils.now());
        gearData.getEngineDataList().add(engineData);
    }

    private double readRpm(RPMCommand rpmCommand) throws IOException, InterruptedException {
        rpmCommand.run(socket.getInputStream(), socket.getOutputStream());
        return Double.parseDouble(rpmCommand.getCalculatedResult());
    }

    private double readSpeed(SpeedCommand speedCommand) throws IOException, InterruptedException {
        speedCommand.run(socket.getInputStream(), socket.getOutputStream());
        return Double.parseDouble(speedCommand.getCalculatedResult());
    }

    public void removeLast() {
        if (gearData.getEngineDataList() != null) {
            gearData.getEngineDataList().remove(gearData.getEngineDataList().size() - 1);
        }
    }

    public GearData getGearData() {
        return gearData;
    }

    public void finish() throws IOException {
        socket.close();
    }
}
