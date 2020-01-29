package org.design.pattern.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: AsynchronizedFuture.java
 * packageName: org.design.pattern.chapter8
 * date: 2020年01月28日  11:40:10
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class AsynchronizedFuture<T> implements Future<T>
{
    private volatile boolean done = false;

    private T result;

    public void done(T result){
        synchronized(this){
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException
    {
        synchronized(this){
            while(!done){
                this.wait();
            }
        }
        return result;
    }
}
