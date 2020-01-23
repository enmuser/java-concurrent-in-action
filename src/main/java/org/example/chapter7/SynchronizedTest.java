package org.example.chapter7;

/**
 * projectName: javaconcurrent
 * fileName: SynchronizedTest.java
 * packageName: org.example.chapter7
 * date: 2020年01月21日  15:59:34
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SynchronizedTest
{
    private final static Object LOCK = new Object();
    public static void main(String[] args)
    {
        Runnable runnable = () -> {
            synchronized(LOCK){
                try
                {
                    Thread.sleep(200_000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runnable,"Thread1");
        Thread thread2 = new Thread(runnable,"Thread2");
        Thread thread3 = new Thread(runnable,"Thread3");
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
