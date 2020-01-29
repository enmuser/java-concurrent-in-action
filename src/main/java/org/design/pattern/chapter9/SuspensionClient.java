package org.design.pattern.chapter9;

/**
 * projectName: javaconcurrent
 * fileName: SuspensionClient.java
 * packageName: org.design.pattern.chapter9
 * date: 2020年01月28日  14:00:26
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SuspensionClient
{
    public static void main(String[] args) throws InterruptedException
    {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue,"ENMUSER").start();
        ServerThread thread = new ServerThread(queue);
        thread.start();
        Thread.sleep(10_000);
        thread.close();
    }
}
