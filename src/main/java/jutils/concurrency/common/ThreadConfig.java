package jutils.concurrency.common;

import java.util.concurrent.ThreadFactory;

public class ThreadConfig implements ThreadFactory {

    public static ThreadConfig newConfig() {
        return new ThreadConfig();
    }

    private int priority = Thread.NORM_PRIORITY;
    private boolean daemon = false;
    private ThreadGroup threadGroup = null;
    private ClassLoader classLoader = null;
    private String name = null;
    private Thread.UncaughtExceptionHandler exceptionHandler = null;

    public int getPriority() {
        return priority;
    }

    public ThreadConfig setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public ThreadConfig setMaxPriority() {
        return setPriority(Thread.MAX_PRIORITY);
    }

    public ThreadConfig setNormPriority() {
        return setPriority(Thread.NORM_PRIORITY);
    }

    public ThreadConfig setMinPriority() {
        return setPriority(Thread.MIN_PRIORITY);
    }

    public boolean isDaemon() {
        return daemon;
    }

    public ThreadConfig setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public ThreadConfig setThreadGroup(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
        return this;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public ThreadConfig setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        return this;
    }

    public String getName() {
        return name;
    }

    public ThreadConfig setName(String name) {
        this.name = name;
        return this;
    }

    public Thread.UncaughtExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public ThreadConfig setExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }

    public ThreadConfig copy() {
        return newConfig()
                .setClassLoader(this.classLoader)
                .setExceptionHandler(this.exceptionHandler)
                .setDaemon(this.daemon)
                .setThreadGroup(this.threadGroup)
                .setName(this.name)
                .setPriority(this.priority);
    }

    @Override
    final public Thread newThread(Runnable r) {
        Thread thread = new Thread(getThreadGroup(), r);
        if(getName()!=null)thread.setName(getName());
        thread.setPriority(getPriority());
        thread.setContextClassLoader(getClassLoader());
        thread.setUncaughtExceptionHandler(getExceptionHandler());
        thread.setDaemon(isDaemon());
        return thread;
    }
}
