package org.example.chapter7;

/**
 * projectName: javaconcurrent
 * fileName: SynchronizedStatic.java
 * packageName: org.example.chapter7
 * date: 2020年01月21日  19:13:42
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SynchronizedStatic
{
    static {
        synchronized(SynchronizedStatic.class){
            System.out.println("static.synchronized");
            try
            {
                System.out.println("====>"+Thread.currentThread().getName());
                Thread.sleep(10_000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public synchronized static void M1(){
        try
        {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized static void M2(){
        try
        {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void M3(){
        try
        {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
