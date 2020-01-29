package org.design.pattern.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: ObserverableRunnable.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  20:59:54
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public abstract class ObserverableRunnable implements Runnable
{
    final protected LifeCycleListener2 listener2;

    public ObserverableRunnable(LifeCycleListener2 listener2)
    {
        this.listener2 = listener2;
    }

    public void notifyChange(RunnableEvent event){
        listener2.onEvent(event);
    }

    public enum RunnableState{
        RUNNING,DONE,ERROR;
    }


    public static class RunnableEvent{
        private final RunnableState state;

        private final Thread thread;

        private final Throwable throwable;

        public RunnableEvent(RunnableState state, Thread thread, Throwable throwable)
        {
            this.state = state;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunnableState getState()
        {
            return state;
        }

        public Thread getThread()
        {
            return thread;
        }

        public Throwable getThrowable()
        {
            return throwable;
        }
    }
}
