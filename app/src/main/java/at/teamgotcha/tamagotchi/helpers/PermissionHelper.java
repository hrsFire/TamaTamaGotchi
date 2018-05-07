package at.teamgotcha.tamagotchi.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import at.teamgotcha.tamagotchi.enums.CustomPermissions;

public class PermissionHelper {

    public static void getAllBluetoothPermissions(Activity cActivity) {

        // check: do you have the permission?
        // Bluetooth
        getSpecificPermission(cActivity, Manifest.permission.BLUETOOTH, CustomPermissions.BLUETOOTH_REQUEST_CODE.getPermissionValue());

        // Bluetooth privileged
        getSpecificPermission(cActivity, Manifest.permission.BLUETOOTH_PRIVILEGED, CustomPermissions.BLUETOOTH_PRIVILED_REQUEST_CODE.getPermissionValue());

        // Bluetooth admin
        getSpecificPermission(cActivity, Manifest.permission.BLUETOOTH_ADMIN, CustomPermissions.BLUETOOTH_ADMIN_REQUEST_CODE.getPermissionValue());

        // All Bluetooth permissions at once
        /*
        String[] requestPermissions = new String[]{
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_PRIVILEGED,
                Manifest.permission.BLUETOOTH_ADMIN
        };

        getAllPermissions(cActivity, requestPermissions, CustomPermissions.ALL_BLUETOOTH_REQUEST_CODE.getPermissionValue());
        */
    }

    public static void getSpecificPermission(Activity cActivity, String permissionManifest, int permissionRequestCode){

        if(ContextCompat.checkSelfPermission(cActivity, permissionManifest) != PackageManager.PERMISSION_GRANTED) {

            // request permission
            ActivityCompat.requestPermissions(cActivity,
                    new String[] { permissionManifest },
                    permissionRequestCode);
        }

    }

    public static void getAllPermissions(Activity cActivity, String[] permissionManifest, int permissionRequestCode){

        /*
        // first: get the actual missing permissions
        List<String> permissionList = new ArrayList<String>();

        // is any of the permissions missing?
        for (String permission : permissionManifest) {

            if(ContextCompat.checkSelfPermission(cActivity, permission) == PackageManager.PERMISSION_GRANTED){

                permissionList.add(permission);
            }
        }

        // second:
        // convert List to String[}
        String[] requestPermissions = new String[permissionList.size()];
        requestPermissions = permissionList.toArray(requestPermissions);

        // third:
        // request permissions
        ActivityCompat.requestPermissions(cActivity, requestPermissions, permissionRequestCode);
        */

        ActivityCompat.requestPermissions(cActivity, permissionManifest, permissionRequestCode);
    }

    public static boolean validateResponse(int[] grantResults){

        // if the request is cancelled, the result arrays are empty
        if(grantResults.length == 0){

            return false;
        }

        // have all permissions been granted?
        else{

            int len = grantResults.length;

            for(int i = 0; i < len; i++){

                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){

                    return false;
                }
            }
        }

        return true;
    }

}
