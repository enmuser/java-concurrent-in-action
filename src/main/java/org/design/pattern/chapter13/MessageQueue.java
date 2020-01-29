package org.design.pattern.chapter13;

import java.util.LinkedList;

/**
 * projectName: javaconcurrent
 * fileName: MessageQueue.java
 * packageName: org.design.pattern.chapter13
 * date: 2020年01月28日  21:27:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class MessageQueue
{
    private final LinkedList<Message> queue;

    private final static int DEFALUT_MAX_VALUE = 100;

    private final int limit;

    public MessageQueue()
    {
        this(DEFALUT_MAX_VALUE);
    }

    public MessageQueue(final int limit)
    {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(final Message message) throws InterruptedException
    {
        synchronized(queue){
            while(queue.size() >= limit){
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException
    {
        synchronized(queue){
            while(queue.isEmpty()){
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return this.limit;
    }

    public int getMessageSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}
