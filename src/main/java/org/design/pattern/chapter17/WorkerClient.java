package org.design.pattern.chapter17;

/**
 * projectName: javaconcurrent
 * fileName: WorkerClient.java
 * packageName: org.design.pattern.chapter17
 * date: 2020年01月29日  13:00:02
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class WorkerClient
{
    public static void main(String[] args)
    {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("William", channel).start();
    }
}
