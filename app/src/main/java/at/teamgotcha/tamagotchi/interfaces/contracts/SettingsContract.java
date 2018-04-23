package at.teamgotcha.tamagotchi.interfaces.contracts;

public interface SettingsContract extends PetObserverContract {
    void showRestartDialog();
    void showLanguageDialog();
    void showVolumeDialog();
    void showNotificationDialog();
    void showSelectPetDialog();
}