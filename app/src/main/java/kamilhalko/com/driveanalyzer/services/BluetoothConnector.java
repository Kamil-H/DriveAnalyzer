package kamilhalko.com.driveanalyzer.services;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolCommand;
import com.github.pires.obd.commands.protocol.TimeoutCommand;
import com.github.pires.obd.enums.ObdProtocols;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import kamilhalko.com.driveanalyzer.R;

public class BluetoothConnector {

    public static void connect(Context context, DeviceConnectionCallback deviceConnectionCallback) {
        if (isBluetoothEnabled()) {
            checkDevices(context, deviceConnectionCallback);
        } else {
            deviceConnectionCallback.onConnectionFailed(context.getString(R.string.BluetoothConnector_bluetooth_disabled));
        }
    }

    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    private static void checkDevices(Context context, DeviceConnectionCallback deviceConnectionCallback) {
        Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        List<String> devicesNames = new ArrayList<>();
        List<BluetoothDevice> deviceList = new ArrayList<>();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                devicesNames.add(device.getName());
                deviceList.add(device);
            }
            showDevicesList(context, deviceList, devicesNames.toArray(new String[devicesNames.size()]), deviceConnectionCallback);
        }
    }

    private static void showDevicesList(final Context context, final List<BluetoothDevice> bluetoothDevices, String[] devicesNames, final DeviceConnectionCallback deviceConnectionCallback) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.select_dialog_singlechoice, devicesNames);
        alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                connect(context, bluetoothDevices.get(position).getAddress(), deviceConnectionCallback);
            }
        });
        alertDialog.setTitle(R.string.BluetoothConnector_choose_device);
        alertDialog.show();
    }

    private static void connect(Context context, String address, DeviceConnectionCallback deviceConnectionCallback) {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (isBluetoothEnabled()) {
            BluetoothDevice device = btAdapter.getRemoteDevice(address);
            UUID SERIAL_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            BluetoothSocket socket = null;

            try {
                socket = device.createRfcommSocketToServiceRecord(SERIAL_UUID);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (socket != null) {
                    socket.connect();
                }
                connectToObdDevice(context, socket, deviceConnectionCallback);
                Log.i("BluetoothConnector","Connected");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Log.e("BluetoothConnector","trying fallback...");
                    socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(device,1);
                    socket.connect();
                    Log.i("BluetoothConnector", "" + socket.toString());
                    connectToObdDevice(context, socket, deviceConnectionCallback);

                    Log.e("BluetoothConnector","Connected");
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                    deviceConnectionCallback.onConnectionFailed("Couldn't connect to Bluetooth device");
                }
            }
        }
    }

    private static void connectToObdDevice(Context context, BluetoothSocket socket, DeviceConnectionCallback deviceConnectionCallback) {
        try {
            EchoOffCommand echoOffCommand = new EchoOffCommand();
            echoOffCommand.run(socket.getInputStream(), socket.getOutputStream());
            Log.e("EchoOffCommand", echoOffCommand.getResult());

            LineFeedOffCommand lineFeedOffCommand = new LineFeedOffCommand();
            lineFeedOffCommand.run(socket.getInputStream(), socket.getOutputStream());
            Log.e("LineFeedOffCommand", lineFeedOffCommand.getResult());

            TimeoutCommand timeoutCommand = new TimeoutCommand(125);
            timeoutCommand.run(socket.getInputStream(), socket.getOutputStream());
            Log.e("TimeoutCommand", timeoutCommand.getResult());

            SelectProtocolCommand selectProtocolCommand = new SelectProtocolCommand(ObdProtocols.AUTO);
            selectProtocolCommand.run(socket.getInputStream(), socket.getOutputStream());

            Log.e("SelectProtocolCommand", selectProtocolCommand.getResult());
            deviceConnectionCallback.onConnected(socket);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            deviceConnectionCallback.onConnectionFailed(context.getString(R.string.BluetoothConnector_not_obd_device));
        }
    }

    public interface DeviceConnectionCallback {
        void onConnected(BluetoothSocket bluetoothSocket);
        void onConnectionFailed(String message);
    }
}
