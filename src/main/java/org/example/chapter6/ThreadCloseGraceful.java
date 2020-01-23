package org.example.chapter6;

/**
 * projectName: javaconcurrent
 * fileName: ThreadCloseGraceful.java
 * packageName: org.example.chapter6
 * date: 2020年01月21日  14:21:56
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadCloseGraceful
{
    private static class Worker extends Thread{

        private volatile boolean flag = true;
        @Override
        public void run()
        {
            while(flag){

            }
            System.out.println("I am Over");
        }

        public void shutDown(){
            this.flag = false;
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
        worker.shutDown();
    }
}
