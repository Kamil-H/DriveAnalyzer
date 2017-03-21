package kamilhalko.com.driveanalyzer.services;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothManager {
    private Context context;

    public BluetoothManager(Context context) {
        this.context = context;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public void checkBoundedDevices() {
        Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                Log.i("device", new Gson().toJson(device));
                BluetoothSocket bluetoothSocket = getSocket(device);
                try {
                    new EchoOffCommand().run(bluetoothSocket.getInputStream(), bluetoothSocket.getOutputStream());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BluetoothSocket getSocket(BluetoothDevice bluetoothDevice) {
        BluetoothSocket bluetoothSocket = null;
        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bluetoothSocket;
    }
}
