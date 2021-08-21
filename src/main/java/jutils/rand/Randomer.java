package jutils.rand;

import jutils.assertion.Assertion;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomer<T> {

    private final Random random = new SecureRandom();
    private final List<T> objects;

    public Randomer(List<T> objects) {
        this.objects = new ArrayList<>(objects);
    }

    public Randomer(T ... objects) {
        this.objects = new ArrayList<>();
        for(T object:objects) {
            this.objects.add(object);
        }
    }

    public int remainingObjects() {
        return objects.size();
    }

    public boolean hasRemainingObjects() {
        return remainingObjects()>0;
    }

    public T getRandomObjectAndRemove() {
        synchronized (objects) {
            Assertion.ifTrue("objects are empty", objects.isEmpty());
            return objects.remove(random.nextInt(objects.size()));
        }
    }

    public T getRandomObject() {
        synchronized (objects) {
            Assertion.ifTrue("objects are empty", objects.isEmpty());
            return objects.get(random.nextInt(objects.size()));
        }
    }

}
