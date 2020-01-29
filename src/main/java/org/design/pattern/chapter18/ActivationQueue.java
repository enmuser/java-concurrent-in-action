package org.design.pattern.chapter18;

import org.design.pattern.chapter17.Request;

import java.util.LinkedList;

/**
 * projectName: javaconcurrent
 * fileName: ActivationQueue.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:06:40
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ActivationQueue
{
    private static final int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue()
    {
        this.methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request){
        while(methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE){
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take(){
        while(methodQueue.isEmpty()){
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        MethodRequest request = methodQueue.removeFirst();
        this.notifyAll();
        return request;
    }
}
