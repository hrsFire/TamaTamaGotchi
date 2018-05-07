package at.teamgotcha.tamagotchi.enums;

public enum CustomPermissions {

    // Bluetooth
    DEFAULT_REQUEST_CODE(0),
    BLUETOOTH_REQUEST_CODE(1),
    BLUETOOTH_PRIVILED_REQUEST_CODE(2),
    BLUETOOTH_ADMIN_REQUEST_CODE(3),
    ALL_BLUETOOTH_REQUEST_CODE(4);

    // https://stackoverflow.com/questions/8811815/is-it-possible-to-assign-numeric-value-to-an-enum-in-java
    private int permissionValue;

    CustomPermissions(int cValue){

        permissionValue = cValue;
    }

    public int getPermissionValue(){

        return permissionValue;
    }



}
