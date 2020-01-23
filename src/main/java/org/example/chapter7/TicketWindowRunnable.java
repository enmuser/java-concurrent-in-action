package org.example.chapter7;

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
    private static int index = 1;

    private static final int MAX = 500;

    private final Object object = new Object();

    @Override
    public void run()
    {
        while(true){
            synchronized(object)
            {
                if(index > MAX) break;
                try
                {
                    Thread.sleep(5);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("柜台：" + Thread.currentThread().getName() + " 叫到的号码 " + (index++));
            }
        }
    }
}
