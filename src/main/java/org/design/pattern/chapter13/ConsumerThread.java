package org.design.pattern.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * projectName: javaconcurrent
 * fileName: ConsumerThread.java
 * packageName: org.design.pattern.chapter13
 * date: 2020年01月28日  21:44:45
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ConsumerThread extends Thread
{
    private final MessageQueue queue;

    private final static Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue queue,int seq)
    {
        super("CONSUMER-"+seq);
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while (true) {
            try
            {
                Message message = queue.take();
                System.out.println(Thread.currentThread().getName() + " take a message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
