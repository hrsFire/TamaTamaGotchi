package at.teamgotcha.tamagotchi.enums;

public enum  Gender {
    MALE(0),
    FEMALE(1);

    public final int value;

    private Gender(int value) {
        this.value = value;
    }
}