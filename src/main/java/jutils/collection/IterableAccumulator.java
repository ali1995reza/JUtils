package jutils.collection;

import java.util.*;

public class IterableAccumulator<T> implements Iterable<T> {

    public static <T> Iterable<T> accumulate(Iterable<T> ... iterables) {
        return new IterableAccumulator<>(iterables);
    }

    public static <T> Iterable<T> accumulate(Collection<Iterable<T>> iterables) {
        return new IterableAccumulator<>(iterables);
    }


    private final List<Iterable<T>> iterables;

    public IterableAccumulator(Iterable<T> ... iterables) {
        this.iterables = new ArrayList<>();
        for(Iterable<T> iterable:iterables){
            this.iterables.add(iterable);
        }
    }

    public IterableAccumulator(Collection<Iterable<T>> iterables) {
        this.iterables = new ArrayList<>();
        for(Iterable<T> iterable:iterables){
            this.iterables.add(iterable);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter<>(iterables.iterator());
    }

    private class Iter<T> implements Iterator<T> {

        private final Iterator<Iterable<T>> iterables;
        private Iterator<T> current;

        private Iter(Iterator<Iterable<T>> iterables) {
            this.iterables = iterables;
            if(iterables.hasNext()) {
                current = iterables.next().iterator();
            } else {
                current = Collections.EMPTY_LIST.iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if(current.hasNext()){
                return true;
            } else if(iterables.hasNext()){
                current = iterables.next().iterator();
                return hasNext();
            }
            return false;
        }

        @Override
        public T next() {
            return current.next();
        }

    }

}
