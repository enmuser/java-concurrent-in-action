package org.example.chapter11;

/**
 * projectName: javaconcurrent
 * fileName: ThreadException.java
 * packageName: org.example.chapter11
 * date: 2020年01月23日  09:17:31
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadException
{
    private final static int A = 10;
    private final static int B = 0;
    public static void main(String[] args)
    {
        Thread thread = new Thread(()->{
            try
            {
                Thread.sleep(5_000);
                int result = A/B;
                System.out.println(result);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        thread.setUncaughtExceptionHandler((t,e)->{
            System.out.println(e);
            System.out.println(t);
        });
        thread.start();

    }
}
