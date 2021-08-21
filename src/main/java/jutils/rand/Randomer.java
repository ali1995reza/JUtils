package jutils.rand;

import jutils.assertion.Assertion;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class Randomer<T> {

    private static class RandomObjectHolder<T> {
        private final T object;
        private int getCount = 0;

        private RandomObjectHolder(T object) {
            this.object = object;
        }

        void increaseGetCount() {
            ++getCount;
        }

        int getCount() {
            return getCount;
        }

    }

    private final Random random = new SecureRandom();
    private final List<RandomObjectHolder<T>> objects;

    public Randomer(List<T> objects) {
        this.objects = new ArrayList<>();
        for(T object : objects) {
            this.objects.add(new RandomObjectHolder<>(object));
        }
    }

    public Randomer(T ... objects) {
        this.objects = new ArrayList<>();
        for(T object:objects) {
            this.objects.add(new RandomObjectHolder<>(object));
        }
    }

    public int remainingObjects() {
        return objects.size();
    }

    public boolean hasRemainingObjects() {
        return remainingObjects()>0;
    }

    public T getRandomObjectAndRemove(int count) {
        synchronized (objects) {
            Assertion.ifTrue("objects are empty", objects.isEmpty());
            int rand = random.nextInt(objects.size());
            RandomObjectHolder<T> object = objects.get(rand);
            object.increaseGetCount();
            if(object.getCount()>=count) {
                objects.remove(rand);
            }
            return object.object;
        }
    }

    public T getRandomObjectAndRemove() {
        return getRandomObjectAndRemove(1);
    }

    public T getRandomObject() {
        synchronized (objects) {
            Assertion.ifTrue("objects are empty", objects.isEmpty());
            int rand = random.nextInt(objects.size());
            RandomObjectHolder<T> object = objects.get(rand);
            object.increaseGetCount();
            return object.object;
        }
    }

}
