package jutils.collection;

import java.util.Iterator;
import java.util.function.Consumer;

public class IteratorMapper<F, T> implements Iterator<T> {

    public static <F, T> Iterator<T> map(Iterator<F> iterator, Mapper<F, T> mapper) {
        return new IteratorMapper<>(iterator, mapper);
    }


    private final Iterator<F> wrapped;
    private final Mapper<F , T> mapper;

    private IteratorMapper(Iterator<F> wrapped, Mapper<F, T> mapper) {
        this.wrapped = wrapped;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return wrapped.hasNext();
    }

    @Override
    public T next() {
        return mapper.map(wrapped.next());
    }

    @Override
    public void remove() {
        wrapped.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }
}
