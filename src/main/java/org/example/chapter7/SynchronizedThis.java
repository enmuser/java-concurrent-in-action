package org.example.chapter7;

/**
 * projectName: javaconcurrent
 * fileName: SynchronizedThis.java
 * packageName: org.example.chapter7
 * date: 2020年01月21日  19:00:59
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SynchronizedThis
{
    public static void main(String[] args)
    {
        ThisLock lock = new ThisLock();
        new Thread(()->{
            lock.M1();
        },"T1").start();

        new Thread(()->{
            lock.M2();
        },"T2").start();
    }
}

class ThisLock{

    public synchronized void M1(){

        try
        {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5_000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized void M2(){

        try
        {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5_000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
