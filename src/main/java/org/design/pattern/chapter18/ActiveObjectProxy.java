package org.design.pattern.chapter18;

import org.design.pattern.chapter8.Future;

/**
 * projectName: javaconcurrent
 * fileName: ActiveObjectProxy.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:22:11
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ActiveObjectProxy implements ActiveObject
{
    private final Servant servant;

    private final SchedulerThread schedulerThread;

    public ActiveObjectProxy(Servant servant, SchedulerThread schedulerThread)
    {
        this.servant = servant;
        this.schedulerThread = schedulerThread;
    }

    @Override
    public Result makeString(int count, char fillChar)
    {
        FutureResult result = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant,result,count,fillChar));
        return result;
    }

    @Override
    public void displayString(String text)
    {
        schedulerThread.invoke(new DisplayStringRequest(servant,text));
    }
}
