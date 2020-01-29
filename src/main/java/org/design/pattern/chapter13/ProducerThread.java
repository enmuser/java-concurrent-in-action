package org.design.pattern.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * projectName: javaconcurrent
 * fileName: ProducerThread.java
 * packageName: org.design.pattern.chapter13
 * date: 2020年01月28日  21:43:06
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ProducerThread extends Thread
{
    private final MessageQueue queue;

    private final static Random random = new Random(System.currentTimeMillis());

    private final AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue queue,int seq)
    {
        super("PRODUCER-"+seq);
        this.queue = queue;
    }

    @Override
    public void run()
    {

        while(true){
            try
            {
                Message message = new Message("Message-"+counter.getAndIncrement());
                queue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
