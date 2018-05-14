package at.teamgotcha.tamagotchi.helpers;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class BluetoothHelper {
    public final static int REQUEST_ENABLE_BT = 1;

    public static BluetoothAdapter getBluethoothAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    public static ArrayList<BluetoothDevice> getPairedDevices(BluetoothAdapter adapter) {
        Set<BluetoothDevice> pairedDevice = adapter.getBondedDevices();
        ArrayList<BluetoothDevice> list = new ArrayList<>(pairedDevice.size());

        if(pairedDevice.size() > 0) {
            for (BluetoothDevice device : pairedDevice) {
                list.add(device);
            }
        }

        return list;
    }

    // duration in seconds
    public static void makeDiscoverable(Activity activity, int duration) {
        // https://developer.android.com/guide/topics/connectivity/bluetooth
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, duration);
        activity.startActivityForResult(discoverableIntent, REQUEST_ENABLE_BT);
    }

    // https://stackoverflow.com/questions/22899475/android-sample-bluetooth-code-to-send-a-simple-string-via-bluetooth
    public static void sendData(String content, BluetoothDevice targetDevice) throws IOException {
        OutputStream outputStream;
        BluetoothAdapter adapter = getBluethoothAdapter();

        if (adapter != null) {
            if (!adapter.isEnabled()) {
                adapter.enable();
            }

            if (adapter.isEnabled()) {
                // https://stackoverflow.com/questions/16457693/the-differences-between-createrfcommsockettoservicerecord-and-createrfcommsocket
                /*BluetoothSocket socket = targetDevice.createInsecureRfcommSocketToServiceRecord(uuids[0].getUuid());*/
                Method createSocket;

                try {
                    targetDevice.createBond();
                    createSocket = targetDevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
                    BluetoothSocket socket = null;
                    socket = (BluetoothSocket) createSocket.invoke(targetDevice, Integer.valueOf(1));
                    socket.connect();
                    outputStream = socket.getOutputStream();

                    outputStream.write(content.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    socket.close();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("error", "Bluetooth is disabled.");
            }
        }
    }
}
