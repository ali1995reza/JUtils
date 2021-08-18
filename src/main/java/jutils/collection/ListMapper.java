package jutils.collection;

import java.util.*;
import java.util.function.Consumer;

public class ListMapper<F, T> implements List<T> {

    public static <F, T> List<T> map(List<F> wrapped, Mapper<F, T> mapper) {
        return new ListMapper<>(wrapped, mapper);
    }

    private static final IllegalStateException UNMODIFIABLE_EXCEPTION = new IllegalStateException("unmodifiable list");
    private final List<F> wrapped;
    private final Mapper<F, T> mapper;

    public ListMapper(List<F> wrapped, Mapper<F, T> mapper) {
        this.wrapped = wrapped;
        this.mapper = mapper;
    }

    @Override
    public int size() {
        return wrapped.size();
    }

    @Override
    public boolean isEmpty() {
        return wrapped.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        for (T t : this) {
            if (t == o || (t != null && t.equals(o))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return IteratorMapper.map(wrapped.iterator(), mapper);
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size()];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = get(i);
        }
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public boolean remove(Object o) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public void clear() {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public T get(int index) {
        return mapper.map(wrapped.get(index));
    }

    @Override
    public T set(int index, T element) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public void add(int index, T element) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public T remove(int index) {
        throw UNMODIFIABLE_EXCEPTION;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            T data = get(i);
            if (o == data || (o != null && o.equals(data))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            T data = get(i);
            if (o == data || (o != null && o.equals(data))) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        return ListIteratorMapper.map(wrapped.listIterator(), mapper);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return ListIteratorMapper.map(wrapped.listIterator(index), mapper);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> list = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            list.add(get(i));
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConvertedList [ ");
        for (int i = 0; i < size(); i++) {
            builder.append(get(i).toString());
            if (i == size() - 1) {
                builder.append(" ");
            } else {
                builder.append(" , ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public final static class ListIteratorMapper<F, T> implements ListIterator<T> {

        public static <F, T> ListIteratorMapper<F, T> map(ListIterator<F> iterator, Mapper<F, T> mapper) {
            return new ListIteratorMapper<>(iterator, mapper);
        }


        private final ListIterator<F> wrapped;
        private final Mapper<F, T> mapper;

        private ListIteratorMapper(ListIterator<F> wrapped, Mapper<F, T> mapper) {
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
        public boolean hasPrevious() {
            return wrapped.hasPrevious();
        }

        @Override
        public T previous() {
            return mapper.map(wrapped.previous());
        }

        @Override
        public int nextIndex() {
            return wrapped.nextIndex();
        }

        @Override
        public int previousIndex() {
            return wrapped.previousIndex();
        }

        @Override
        public void remove() {
            wrapped.remove();
        }

        @Override
        public void set(T t) {
            throw UNMODIFIABLE_EXCEPTION;
        }

        @Override
        public void add(T t) {
            throw UNMODIFIABLE_EXCEPTION;
        }

    }
}