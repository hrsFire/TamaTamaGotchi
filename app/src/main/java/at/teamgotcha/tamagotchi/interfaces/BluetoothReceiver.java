package at.teamgotcha.tamagotchi.interfaces;

import java.io.InputStream;

public interface BluetoothReceiver {
    void receiveBluetoothData(InputStream inputStream);
}