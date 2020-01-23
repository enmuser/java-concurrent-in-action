package org.example.chapter6;

/**
 * projectName: javaconcurrent
 * fileName: ThreadCloseGraceful2.java
 * packageName: org.example.chapter6
 * date: 2020年01月21日  14:30:19
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadCloseGraceful2
{
    private static class Worker extends Thread{
        @Override
        public void run()
        {
            while(true){
                if(Thread.interrupted())
                    break;
                try
                {
                    Thread.sleep(10_000);
                }
                catch(InterruptedException e)
                {
                    //e.printStackTrace();
                    break;
                }
            }
            System.out.println("I AM Over");
        }
    }

    public static void main(String[] args)
    {
        Worker worker = new Worker();
        worker.start();
        try
        {
            Thread.sleep(10000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        worker.interrupt();
    }
}
