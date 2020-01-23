package org.example.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * projectName: javaconcurrent
 * fileName: BooleanLock.java
 * packageName: org.example.chapter10
 * date: 2020年01月22日  22:16:13
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class BooleanLock implements Lock
{
    private boolean islocked = false;
    private  Collection<Thread> blockedThreadCollection = new ArrayList<>();
    private Thread currentThread;

    public BooleanLock(boolean islocked)
    {
        this.islocked = islocked;
    }

    @Override
    public synchronized void lock() throws InterruptedException
    {
        while(islocked){
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.islocked = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException
    {
        if(mills <= 0){
            lock();
        }
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;

        while(islocked){
            if(hasRemaining < 0)
                throw new TimeOutException(Thread.currentThread()+ " Time out");
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
        }

        blockedThreadCollection.remove(Thread.currentThread());
        this.islocked = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock()
    {
        if(Thread.currentThread() == currentThread){
            this.islocked = false;
            Optional.of(Thread.currentThread() + " release the lock monitor ")
            .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread()
    {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize()
    {
        return blockedThreadCollection.size();
    }
}
