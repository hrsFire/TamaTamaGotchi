package at.teamgotcha.tamagotchi.base;

import java.util.EnumSet;
import java.util.Vector;

import at.teamgotcha.tamagotchi.interfaces.Observable;
import at.teamgotcha.tamagotchi.interfaces.Observer;

public abstract class ObservableSubject<T extends Observable<T,E>, E extends Enum<E>> implements Observable<T, E> {
    private Vector<Observer<T, E>> observers;
    private EnumSet<E> changedProperties;
    private Class<E> clazz;
    protected T object;
    private boolean hasChanged;
    private EnumSet<E> allProperties;
    private EnumSet<E> zeroProperties;

    public ObservableSubject(Class<E> clazz) {
        observers = new Vector<>();
        this.clazz = clazz;
        allProperties = EnumSet.allOf(clazz);
        zeroProperties = EnumSet.noneOf(clazz);
        clearChanged();
    }

    @Override
    public synchronized void register(Observer<T, E> observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public synchronized void unregister(Observer<T, E> observer) {
        observers.remove(observer);
    }

    @Override
    public void unregisterAll() {
        observers.removeAllElements();
    }

    @Override
    public boolean hasFullyChanged() {
        return changedProperties.retainAll(allProperties);
    }

    @Override
    public void notifyObservers() {
        Observer<T, E>[] cloned;
        EnumSet<E> tmpChangedProperties;

        synchronized (this) {
            if (!hasChanged()) {
                return;
            }

            cloned = (Observer<T, E>[]) observers.toArray();
            tmpChangedProperties = changedProperties;
            clearChanged();
        }

        for(Observer<T, E> item : cloned) {
            if (hasFullyChanged()) {
                item.changed(getObject());
            } else {
                item.changed(tmpChangedProperties);
            }
        }
    }

    @Override
    public synchronized boolean hasChanged() {
        return hasChanged;
    }

    @Override
    public synchronized int size() {
        return observers.size();
    }

    @Override
    public T getObject() {
        return object;
    }

    @Override
    public void addChangedProperties(EnumSet<E> changed) {
        boolean hasChanged = false;

        for (E item : changed) {
            if (!changedProperties.contains(item)) {
                changedProperties.add(item);
                hasChanged = true;
            }
        }

        if (hasChanged) {
            setChanged();
        }
    }

    protected synchronized void setChanged() {
        hasChanged = true;
    }

    protected synchronized void clearChanged() {
        hasChanged = false;
        changedProperties = zeroProperties;
    }

    protected void setObject(T newObject) {
        object = newObject;
        changedProperties = allProperties;
        setChanged();
    }
}