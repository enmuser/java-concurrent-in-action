package org.example.chapter6;

import javax.print.attribute.standard.RequestingUserName;

/**
 * projectName: javaconcurrent
 * fileName: ThreadService.java
 * packageName: org.example.chapter6
 * date: 2020年01月21日  15:01:23
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadService
{
    private static Thread executedThread;
    private static boolean finish = false;

    private static long startTime;

    public void execute(Runnable runnable){
        executedThread = new Thread(){
            @Override
            public void run()
            {
                Thread runner = new Thread(runnable);
                runner.setDaemon(true);

                runner.start();
                try
                {
                    runner.join();
                    finish = true;
                }
                catch(InterruptedException e)
                {
                    //e.printStackTrace();
                }
            }
        };
        executedThread.start();

    }

    public void shutDown(long millis){
        startTime = System.currentTimeMillis();
        while(!finish){
            if((System.currentTimeMillis()- startTime)>millis){
                System.out.println("时间超时了，该关了");
                executedThread.interrupt();
                break;
            }

            try
            {
                executedThread.sleep(1);
            }
            catch(InterruptedException e)
            {
                System.out.println("executedThread is interrupted");

            }
        }
        finish = false;
    }

}
