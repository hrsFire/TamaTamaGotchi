package at.teamgotcha.tamagotchi.pets;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

import at.teamgotcha.tamagotchi.helpers.BluetoothHelper;
import at.teamgotcha.tamagotchi.interfaces.BluetoothReceiver;

public class PetBluetoothServer extends Thread {
    private final BluetoothServerSocket mmServerSocket;
    private BluetoothReceiver receiver;

    public PetBluetoothServer(BluetoothReceiver receiver) {
        this.receiver = receiver;

        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = BluetoothHelper.getBluethoothAdapter().listenUsingRfcommWithServiceRecord("petServer", UUID.randomUUID());
        } catch (IOException e) {
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
            }

            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                try {
                    receiver.receiveBluetoothData(socket.getInputStream());
                    mmServerSocket.close();
                } catch (IOException e) {
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
        }
    }
}