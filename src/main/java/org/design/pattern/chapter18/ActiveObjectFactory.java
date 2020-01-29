package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: ActiveObjectFactory.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:32:59
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public final class ActiveObjectFactory
{
    private ActiveObjectFactory(){

    }

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(activationQueue);
        ActiveObjectProxy activeObjectProxy = new ActiveObjectProxy(servant,schedulerThread);
        schedulerThread.start();
        return activeObjectProxy;
    }
}
