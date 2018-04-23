package at.teamgotcha.tamagotchi.interfaces.contracts;

import at.teamgotcha.tamagotchi.interfaces.contracts.PetObserverContract;

public interface RestartContract extends PetObserverContract {
    void restartGame();
    void cancelRestartGame();
}