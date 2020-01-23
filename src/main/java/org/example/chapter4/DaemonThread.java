package org.example.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: DeamonThread.java
 * packageName: org.example.chapter4
 * date: 2020年01月20日  23:39:48
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DaemonThread
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread thread = new Thread(){
            @Override
            public void run()
            {
                try
                {
                    System.out.println(Thread.currentThread().getName()+" running");
                    Thread.sleep(1000000);
                    System.out.println(Thread.currentThread().getName()+" done");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        System.out.println(Thread.currentThread().getName()+" running");
        Thread.sleep(10_000);
        System.out.println(Thread.currentThread().getName()+" done");
    }
}
