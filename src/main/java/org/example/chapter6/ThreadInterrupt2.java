package org.example.chapter6;

/**
 * projectName: javaconcurrent
 * fileName: ThreadInterrupt2.java
 * packageName: org.example.chapter6
 * date: 2020年01月21日  13:56:04
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadInterrupt2
{

    public static void main(String[] args)
    {
        Thread thread = new Thread(()->{
            while(true){}
        });



        thread.start();
        Thread main = Thread.currentThread();
        Thread thread1 = new Thread(()->{
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                System.out.println("Thread1 Interrupt");
            }
            main.interrupt();
        });
        thread1.start();
        try
        {
            thread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.out.println("Thread Join Interrupt");
        }
    }
}
