package org.example.chapter9;

import java.util.stream.Stream;

/**
 * projectName: javaconcurrent
 * fileName: DifferenceOfSleepAndWait.java
 * packageName: org.example.chapter9
 * date: 2020年01月22日  10:06:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DifferenceOfSleepAndWait
{

    final static Object LOCK = new Object();
    public static void main(String[] args)
    {
        Stream.of("T1","T2").forEach(name -> new Thread(name){
            @Override
            public void run()
            {
//                M1();
                M2();
            }
        }.start());
    }

    public static void M1(){
        synchronized(LOCK)
        {
            try
            {
                System.out.println("The thread " +Thread.currentThread().getName()+" enter. ");
                Thread.sleep(5_000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void M2(){
        synchronized(LOCK){
            try
            {
                System.out.println("The thread " +Thread.currentThread().getName()+" enter. ");
                LOCK.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
