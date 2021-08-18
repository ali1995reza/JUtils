package jutils.concurrency.threaddispatcher;

public interface SyncConsumer<I , T> {


    void accept(I syncId, T data);
}
