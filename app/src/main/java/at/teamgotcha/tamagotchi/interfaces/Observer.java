package at.teamgotcha.tamagotchi.interfaces;

import java.util.EnumSet;

public interface Observer<T, E extends Enum<E>> {
    /**
     * Indicates that an entire object has changed
     * @param value     The object that has changed.
     */
    void changed(T value);

    /**
     * Indicates that only a fraction of an object has changed.
     * @param properties    The properties that has changed.
     */
    void changed(EnumSet<E> properties);
}