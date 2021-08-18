package jutils.common;

public interface Attachable {

    Object attach(Object attachment);

    <T> T attachment();

    <T> T attachment(Class<T> cls);
}
