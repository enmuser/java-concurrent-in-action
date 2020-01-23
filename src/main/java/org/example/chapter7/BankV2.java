package org.example.chapter7;


/**
 * projectName: javaconcurrent
 * fileName: BankV2.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  20:02:39
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class BankV2
{
    public static void main(String[] args)
    {
        final TicketWindowRunnable runnable = new TicketWindowRunnable();

        Thread thread1 = new Thread(runnable,"一号柜台");
        thread1.start();
        Thread thread2 = new Thread(runnable,"二号柜台");
        thread2.start();
        Thread thread3 = new Thread(runnable,"三号柜台");
        thread3.start();
    }
}
