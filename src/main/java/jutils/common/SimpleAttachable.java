package jutils.common;

public class SimpleAttachable implements Attachable {

    private Object attachment;


    @Override
    public Object attach(Object attachment) {
        Object old = this.attachment;
        this.attachment = attachment;
        return old;
    }

    @Override
    public <T> T attachment() {
        return (T)attachment;
    }

    @Override
    public <T> T attachment(Class<T> cls) {
        return (T)attachment;
    }
}
