package org.example.chapter7;

/**
 * projectName: javaconcurrent
 * fileName: BankV3.java
 * packageName: org.example.chapter7
 * date: 2020年01月21日  17:59:16
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class BankV3
{
    public static void main(String[] args)
    {
        final SynchronizedRunnable runnable = new SynchronizedRunnable();

        Thread thread1 = new Thread(runnable,"一号柜台");
        thread1.start();
        Thread thread2 = new Thread(runnable,"二号柜台");
        thread2.start();
        Thread thread3 = new Thread(runnable,"三号柜台");
        thread3.start();
    }
}
