package at.teamgotcha.tamagotchi.interfaces;

import java.util.EnumSet;

public interface Observable<T, E extends Enum<E>> {
    void register(Observer<T, E> observer);
    void unregister(Observer<T, E> observer);
    void unregisterAll();
    boolean hasFullyChanged();
    boolean hasChanged();
    int size();
    void addChangedProperties(EnumSet<E> changed);

    void notifyObservers();
    T getObject();
}