package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: SchedulerThread.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:18:05
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SchedulerThread extends Thread
{
    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue)
    {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request){
        this.activationQueue.put(request);
    }

    @Override
    public void run()
    {
        while(true){
          activationQueue.take().execute();
        }
    }
}
