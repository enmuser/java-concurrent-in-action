package org.design.pattern.chapter8;

import java.util.function.Consumer;

/**
 * projectName: javaconcurrent
 * fileName: FutureService.java
 * packageName: org.design.pattern.chapter8
 * date: 2020年01月28日  11:30:29
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class FutureService
{
    public <T> Future<T> submit(final FutureTask<T> task){
        AsynchronizedFuture<T> future = new AsynchronizedFuture<>();
        new Thread(()->{
            T result = task.call();
            future.done(result);
        }).start();
        return future;
    }

    public <T> Future<T> submit(final FutureTask<T> task,final Consumer<T> consumer){
        AsynchronizedFuture<T> future = new AsynchronizedFuture<>();
        new Thread(()->{
            T result = task.call();
            future.done(result);
            consumer.accept(result);
        }).start();
        return future;
    }
}
