package org.example.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: SimpleThreadPool.java
 * packageName: org.example.chapter13
 * date: 2020年01月23日  11:59:51
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SimpleThreadPool
{
    private final int size;

    //线程池默认大小
    private static final int DEFAULT_SIZE = 10;

    private static volatile int sequence = 0;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL_";

    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("POOL_GROUP");

    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static final List<WorkerThread> WORKER_THREADS = new ArrayList<>();

    public SimpleThreadPool()
    {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size)
    {
        this.size = size;
        init();
    }
    private void init(){
        for(int i = 0; i < size; i++)
        {
            createWorkerTask();
        }
    }

    private void createWorkerTask(){
        WorkerThread workerThread = new WorkerThread(THREAD_GROUP,THREAD_PREFIX+(sequence++));
        workerThread.start();
        WORKER_THREADS.add(workerThread);
    }

    public void submitTask(Runnable runnable){
        synchronized(TASK_QUEUE){
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private enum TaskState{
        FREE,RUNNING,BLOCKED,DEAD
    }

    private static class WorkerThread extends Thread{

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerThread(ThreadGroup group, String name)
        {
            super(group, name);
        }

        @Override
        public void run()
        {
            OUTER:
            while(this.taskState != TaskState.DEAD && this.taskState == TaskState.FREE){
                Runnable runnable;
                synchronized(TASK_QUEUE){
                    while(TASK_QUEUE.isEmpty()){
                        try
                        {
                            this.taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        }
                        catch(InterruptedException e)
                        {
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if(runnable != null){
                    this.taskState = TaskState.RUNNING;
                    runnable.run();
                    this.taskState = TaskState.FREE;
                }
            }
        }

        public TaskState getTaskState(){
            return this.taskState;
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args)
    {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        IntStream.rangeClosed(0,40)
                .forEach(i -> threadPool.submitTask(()->{
                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" started. ");
                    try
                    {
                        Thread.sleep(10_000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" finished");
                }));
    }
}
