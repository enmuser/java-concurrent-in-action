package org.example.chapter10;

import java.util.Optional;

/**
 * projectName: javaconcurrent
 * fileName: SynchronizedProblem.java
 * packageName: org.example.chapter10
 * date: 2020年01月22日  23:12:22
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SynchronizedProblem
{

    public static void main(String[] args) throws InterruptedException
    {
        new Thread(()->{
            run();
        }).start();

        Thread.sleep(1000);

        Thread thread = new Thread(()->{
            run();
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    public static synchronized void run(){
        Optional.of(Thread.currentThread()+" is running...")
        .ifPresent(System.out::println);
        while(true){


        }
    }
}
