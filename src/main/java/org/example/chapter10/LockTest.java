package org.example.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * projectName: javaconcurrent
 * fileName: LockTest.java
 * packageName: org.example.chapter10
 * date: 2020年01月22日  22:30:50
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class LockTest
{
    final static BooleanLock booleanLock = new BooleanLock(false);

    public static void main(String[] args)
    {

        Stream.of("T1","T2","T3","T4").forEach(name->{
            new Thread(()->{
                try
                {
                    booleanLock.lock(1000L);
                    Optional.of(Thread.currentThread().getName()+" have the lock Monitor")
                    .ifPresent(System.out::println);
                    work();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                catch(Lock.TimeOutException e)
                {
                    Optional.of(Thread.currentThread().getName() + " Time Out")
                    .ifPresent(System.out::println);
                }
                finally
                {
                    booleanLock.unlock();
                }
            },name).start();
        });

    }

    public static void work() throws InterruptedException
    {
        Optional.of(Thread.currentThread().getName() + " is working...")
                .ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
