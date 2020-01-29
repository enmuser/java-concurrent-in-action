package org.design.pattern.chapter17;

import java.util.Random;

/**
 * projectName: javaconcurrent
 * fileName: TransportThread.java
 * packageName: org.design.pattern.chapter17
 * date: 2020年01月29日  12:18:25
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TransportThread extends Thread
{
    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel)
    {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run()
    {
        try
        {
            for(int i = 0; true ; i++)
            {
                Request request = new Request(getName(),i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
