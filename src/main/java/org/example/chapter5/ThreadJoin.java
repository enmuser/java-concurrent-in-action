package org.example.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: ThreadJoin.java
 * packageName: org.example.chapter5
 * date: 2020年01月21日  10:49:51
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadJoin
{
    public static void main(String[] args) throws InterruptedException
    {
        /**
         * Join 的含义是 相对于当前main线程而言， 只有join的线程执行完成，才执行当前线程
         */
        Thread thread = new Thread(()->{
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"==>"+i));
        });
        Thread thread2 = new Thread(()->{
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"==>"+i));
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();

        Optional.of("All thread joined is running completely").ifPresent(System.out::println);
        IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"==>"+i));
    }
}
