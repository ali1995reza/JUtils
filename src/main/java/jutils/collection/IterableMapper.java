package jutils.collection;

import java.util.Iterator;

public class IterableMapper<F , T> implements Iterable<T> {

    private final Iterable<F> iterable;
    private final Mapper<F, T> mapper;

    public IterableMapper(Iterable<F> iterable, Mapper<F, T> mapper) {
        this.iterable = iterable;
        this.mapper = mapper;
    }

    @Override
    public Iterator<T> iterator() {
        return IteratorMapper.map(iterable.iterator(), mapper);
    }
}
