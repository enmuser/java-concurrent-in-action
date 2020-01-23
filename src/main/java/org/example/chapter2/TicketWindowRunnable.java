package org.example.chapter2;

/**
 * projectName: javaconcurrent
 * fileName: TicketWindowRunnable.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  20:03:04
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TicketWindowRunnable implements Runnable
{
    private int index = 1;

    private static final int MAX = 50;



    @Override
    public void run()
    {
        while(index <= MAX){
            System.out.println("柜台："+Thread.currentThread().getName()+" 叫到的号码 "+(index++));
        }
    }
}
