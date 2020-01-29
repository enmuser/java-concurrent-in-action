package org.design.pattern.chapter9;

import java.util.Random;

/**
 * projectName: javaconcurrent
 * fileName: ServerThread.java
 * packageName: org.design.pattern.chapter9
 * date: 2020年01月28日  13:48:46
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ServerThread extends Thread
{
    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = true;

    public ServerThread(RequestQueue queue)
    {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run()
    {
        while(flag){
            Request request = queue.getRequest();
            if(null == request){
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server -> "+ request.getValue());

            try
            {
                Thread.sleep(random.nextInt(1000));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                return;
            }
        }

    }

    public void close(){
        this.flag = false;
        this.interrupt();
    }
}
