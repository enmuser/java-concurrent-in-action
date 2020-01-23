package org.example.chapter4;

import java.util.Optional;

/**
 * projectName: javaconcurrent
 * fileName: ThreadPriority.java
 * packageName: org.example.chapter4
 * date: 2020年01月21日  00:43:11
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadPriority
{
    public static void main(String[] args)
    {
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 1000; i++)
            {
                Optional.of(Thread.currentThread().getName()+"Index"+i).ifPresent(System.out::println);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(()->{
            for(int i = 0; i < 1000; i++)
            {
                Optional.of(Thread.currentThread().getName()+"Index"+i).ifPresent(System.out::println);
            }
        });

        t2.setPriority(Thread.NORM_PRIORITY);
        Thread t3 = new Thread(()->{
            for(int i = 0; i < 1000; i++)
            {
                Optional.of(Thread.currentThread().getName()+"Index"+i).ifPresent(System.out::println);
            }
        });
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
