package jutils.rand;

import jutils.assertion.Assertion;

import java.security.SecureRandom;
import java.util.*;

public class Randomer<T> {

    private final static Random RANDOM = new SecureRandom();

    public static <T> Randomer<T> of(T ... objects) {
        return new Randomer<>(objects);
    }

    public static <T> Randomer<T> of(Collection<T> objects) {
        return new Randomer<>(objects);
    }

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

    private final Random random;
    private final List<RandomObjectHolder<T>> objects;

    public Randomer(Random random, Collection<T> objects) {
        Assertion.ifNull("random is null", random);
        this.random = random;
        this.objects = new ArrayList<>();
        for(T object : objects) {
            this.objects.add(new RandomObjectHolder<>(object));
        }
    }

    public Randomer(Collection<T> objects) {
        this(RANDOM, objects);
    }


    public Randomer(Random random, T ... objects) {
        Assertion.ifNull("random is null", random);
        this.random = random;
        this.objects = new ArrayList<>();
        for(T object:objects) {
            this.objects.add(new RandomObjectHolder<>(object));
        }
    }

    public Randomer(T ... objects) {
        this(RANDOM, objects);
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
