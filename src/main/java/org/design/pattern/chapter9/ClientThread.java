package org.design.pattern.chapter9;

import java.util.Random;

/**
 * projectName: javaconcurrent
 * fileName: ClientThread.java
 * packageName: org.design.pattern.chapter9
 * date: 2020年01月28日  13:31:55
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ClientThread extends Thread
{
    private final RequestQueue queue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue queue,String sendValue)
    {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
        this.sendValue = sendValue;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < 15; i++)
        {
            System.out.println("Client -> request "+sendValue);
            queue.putRequest(new Request(sendValue));
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
