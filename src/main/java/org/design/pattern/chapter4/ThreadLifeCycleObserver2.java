package org.design.pattern.chapter4;

import java.util.List;

/**
 * projectName: javaconcurrent
 * fileName: ThreadLifeCycleObserver2.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  21:10:29
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadLifeCycleObserver2 implements LifeCycleListener2
{
    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if(null == ids || ids.isEmpty())
            return;
        ids.stream().forEach(id->{
            new Thread(new ObserverableRunnable(this)
            {
                @Override
                public void run()
                {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));

                    try {
                        notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                        System.out.println("query for the id " + id);
                        Thread.sleep(1000L);
                        notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));
                    }
                }
            },id).start();
        });
    }
    @Override
    public void onEvent(ObserverableRunnable.RunnableEvent event)
    {
        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");
            if (event.getThrowable() != null) {
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getThrowable().printStackTrace();
            }
        }
    }
}
