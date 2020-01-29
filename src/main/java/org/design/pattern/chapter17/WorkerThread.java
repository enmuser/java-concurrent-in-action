package org.design.pattern.chapter17;

import java.util.Random;

/**
 * projectName: javaconcurrent
 * fileName: WorkerThread.java
 * packageName: org.design.pattern.chapter17
 * date: 2020年01月29日  11:43:24
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class WorkerThread extends Thread
{
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());
    public WorkerThread(String name, Channel channel)
    {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run()
    {
        while(true){
            channel.take().execute();
            try
            {
                Thread.sleep(random.nextInt(1000));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
