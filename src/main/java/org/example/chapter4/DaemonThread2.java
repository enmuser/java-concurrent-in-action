package org.example.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: DaemonThread2.java
 * packageName: org.example.chapter4
 * date: 2020年01月21日  00:12:47
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DaemonThread2
{
    public static void main(String[] args)
    {
        Thread thread = new Thread(()->{
            Thread innerThread = new Thread(()->{
                while(true){
                    System.out.println("Do something for health check");
                    try
                    {
                        Thread.sleep(2_000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            innerThread.setDaemon(true);
            innerThread.start();
            try
            {
                Thread.sleep(5_000);
                System.out.println("I am over");
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
