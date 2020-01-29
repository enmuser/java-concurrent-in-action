package org.design.pattern.chapter17;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

/**
 * projectName: javaconcurrent
 * fileName: Channel.java
 * packageName: org.design.pattern.chapter17
 * date: 2020年01月29日  11:42:56
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Channel
{
    private final static int  MAX_REQUEST = 10;

    private final Request[] requestQueue;

    private int head;

    private int tail;

    private int count;

    private final WorkerThread[] workPool;

    public Channel(int wokers)
    {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workPool = new WorkerThread[wokers];
        this.init();
    }

    private void init()
    {
        for(int i = 0; i <workPool.length ; i++)
        {
            workPool[i] = new WorkerThread("Worker-"+i,this);
        }
    }

    public void startWorker(){
        Arrays.asList(workPool).stream().forEach(WorkerThread::start);
    }

    public synchronized void put(Request request){
        while(count >= requestQueue.length){
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        this.requestQueue[tail] = request;
        this.tail = (tail+1)%requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take(){
        while(count <= 0){
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        Request request =  this.requestQueue[head];
        this.head = (head+1)%requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
