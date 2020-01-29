package org.design.pattern.chapter10;

import java.util.Random;

/**
 * projectName: javaconcurrent
 * fileName: ThreadLocalComplexTest.java
 * packageName: org.design.pattern.chapter10
 * date: 2020年01月28日  15:13:13
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadLocalComplexTest
{
    private final static ThreadLocal<String> localThread = new ThreadLocal<>();

    private final static Random randon = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException
    {

        Thread t1 = new Thread(()->{
            localThread.set("Thread-T1");
            try
            {
                Thread.sleep(3_000);
                System.out.println(Thread.currentThread().getName()+" "+localThread.get());
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            localThread.set("Thread-T2");
            try
            {
                Thread.sleep(3_000);
                System.out.println(Thread.currentThread().getName()+" "+localThread.get());
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("====================================");
        System.out.println(Thread.currentThread().getName()+" "+localThread.get());


    }
}
