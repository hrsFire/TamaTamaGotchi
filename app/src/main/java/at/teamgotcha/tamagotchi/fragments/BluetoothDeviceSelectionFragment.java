package at.teamgotcha.tamagotchi.fragments;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.helpers.BluetoothHelper;
import at.teamgotcha.tamagotchi.interfaces.contracts.BluetoothDeviceSelectionContract;

public class BluetoothDeviceSelectionFragment extends ContractV4Fragment<BluetoothDeviceSelectionContract> {
    private ViewGroup bluetoothDevicesListLayout;
    private BootstrapButton cancelButton;

    private BluetoothAdapter bluetoothAdapter;
    private List<BluetoothDevice> bluetoothDeviceList;

    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (!bluetoothDeviceList.contains(device)) {
                    bluetoothDeviceList.add(device);

                    addDeviceToList(device);
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            }
        }
    };

    private void addDeviceToList(final BluetoothDevice device) {
        if (!bluetoothDeviceList.contains(device)) {
            bluetoothDeviceList.add(device);

            View view = getLayoutInflater().inflate(R.layout.bluetooth_device_layout, bluetoothDevicesListLayout);
            BootstrapButton bluetoothDeviceButton = view.findViewById(R.id.bluetooth_device_button);
            bluetoothDeviceButton.setText(device.getName());

            bluetoothDeviceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pairDevice(device);
                }
            });
        }
    }

    public BluetoothDeviceSelectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bluetooth_device_selection, container, false);

        bluetoothDevicesListLayout = view.findViewById(R.id.bluetooth_devices_list_layout);
        cancelButton = view.findViewById(R.id.cancel_button);

        initialize();
        setListeners();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        //bluetoothAdapter.cancelDiscovery();
        //getContext().unregisterReceiver(bluetoothReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();

        bluetoothAdapter = BluetoothHelper.getBluethoothAdapter();
        bluetoothDeviceList = new LinkedList<>();

        /*ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getContext().registerReceiver(bluetoothReceiver, filter);*/

        ArrayList<BluetoothDevice> bluetoothDevices = BluetoothHelper.getPairedDevices(bluetoothAdapter);

        for (BluetoothDevice device : bluetoothDevices) {
            addDeviceToList(device);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        /*if (hidden) {
            bluetoothAdapter.cancelDiscovery();
        } else {
            bluetoothAdapter.startDiscovery();
        }*/
    }

    private void initialize() {
    }

    private void setListeners() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().closeBluetoothDeviceSelectionView();
            }
        });
    }

    private void pairDevice(BluetoothDevice device) {
        getContract().devicePaired(device);
    }
}