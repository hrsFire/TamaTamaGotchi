package at.teamgotcha.tamagotchi.interfaces.contracts;

import android.bluetooth.BluetoothDevice;

public interface BluetoothDeviceSelectionContract {
    void closeBluetoothDeviceSelectionView();
    void devicePaired(BluetoothDevice device);
}
