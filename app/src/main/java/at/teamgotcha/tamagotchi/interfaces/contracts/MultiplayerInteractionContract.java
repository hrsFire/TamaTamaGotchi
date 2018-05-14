package at.teamgotcha.tamagotchi.interfaces.contracts;

import android.bluetooth.BluetoothDevice;

public interface MultiplayerInteractionContract extends PetObserverContract {
    BluetoothDevice getActiveBluetoothDevice();
}