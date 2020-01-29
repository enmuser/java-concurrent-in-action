package org.design.pattern.chapter9;

import java.util.LinkedList;
import java.util.Queue;

/**
 * projectName: javaconcurrent
 * fileName: RequestQueue.java
 * packageName: org.design.pattern.chapter9
 * date: 2020年01月28日  13:18:39
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class RequestQueue
{
    final private LinkedList<Request> queues = new LinkedList<>();

    public Request getRequest(){
        synchronized(queues){
            while(queues.size() <= 0){
                try
                {
                    queues.wait();
                }
                catch(InterruptedException e)
                {
                   return null;
                }
            }
            Request request = queues.removeFirst();
            return request;
        }
    }

    public void putRequest(Request request){
        synchronized(queues){
            queues.addLast(request);
            queues.notifyAll();
        }
    }
}
