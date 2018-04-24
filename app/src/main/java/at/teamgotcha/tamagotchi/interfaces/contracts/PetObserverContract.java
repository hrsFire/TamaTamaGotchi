package at.teamgotcha.tamagotchi.interfaces.contracts;

import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.base.ObservableSubject;
import at.teamgotcha.tamagotchi.enums.PetProperties;

public interface PetObserverContract {
    ObservableSubject<Pet, PetProperties> getPetObserver();
}