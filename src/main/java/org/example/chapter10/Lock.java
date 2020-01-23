package org.example.chapter10;

import java.util.Collection;

/**
 * projectName: javaconcurrent
 * fileName: Lock.java
 * packageName: org.example.chapter10
 * date: 2020年01月22日  21:56:29
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public interface Lock
{
    class TimeOutException extends Exception{
        public TimeOutException(String message)
        {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException,TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
