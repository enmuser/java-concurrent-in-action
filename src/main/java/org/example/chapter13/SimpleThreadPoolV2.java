package org.example.chapter13;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: SimpleThreadPoolV2.java
 * packageName: org.example.chapter13
 * date: 2020年01月23日  13:33:25
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SimpleThreadPoolV2
{
    private final int size;

    private final int taskQueueSize;

    private static final int DEFAULT_SIZE = 10;

    private static volatile int sequeue = 0;

    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL_";
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("POOL_GROUP");

    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static final List<WorkerThread> WORKER_THREADS = new ArrayList<>();

    private final DiscardPolicy discardPolicy;

    private static final DiscardPolicy DEFAULT_DISCARDPOLICY = ()->{
        throw new DiscardException("Pool is full,this task is discarded");
    };

    private volatile boolean destory = false;


    public SimpleThreadPoolV2()
    {
        this(DEFAULT_SIZE,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARDPOLICY);
    }

    public SimpleThreadPoolV2(int size, int taskQueueSize, DiscardPolicy discardPolicy)
    {
        this.size = size;
        this.taskQueueSize = taskQueueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init(){
        for(int i = 0; i < size; i++)
        {
            createWorkerTask();
        }
    }

    public void submitTask(Runnable runnable){
        if(destory)
            throw new IllegalStateException("The thread pool already destory and not allow submit task .");
        synchronized(TASK_QUEUE){
            if(TASK_QUEUE.size() > taskQueueSize)
                discardPolicy.discard();
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkerTask(){
        WorkerThread workerThread = new WorkerThread(THREAD_GROUP,THREAD_PREFIX+(sequeue++));
        workerThread.start();
        WORKER_THREADS.add(workerThread);
    }

    private enum TaskState{
        FREE,RUNNING,BLOCKED,DEAD
    }

    public static class DiscardException extends RuntimeException{
        public DiscardException(String message)
        {
            super(message);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    public void shudDownThreadPool() throws InterruptedException
    {
        while(!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }
        int intValue = WORKER_THREADS.size();
        while(intValue > 0){
            for(WorkerThread workerThread: WORKER_THREADS)
            {
                if(workerThread.taskState == TaskState.BLOCKED){
                    workerThread.interrupt();
                    workerThread.close();
                    intValue--;
                }else{
                    Thread.sleep(10);
                }
            }
        }
        this.destory = true;
        System.out.println("The thread pool disposed");
    }

    public int getSize()
    {
        return size;
    }

    public int getTaskQueueSize()
    {
        return taskQueueSize;
    }

    public boolean destory(){
        return this.destory;
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

    public static void main(String[] args) throws InterruptedException
    {
        //拒绝策略测试
//        SimpleThreadPoolV2 threadPool = new SimpleThreadPoolV2(6,10,DEFAULT_DISCARDPOLICY);
//        IntStream.rangeClosed(0,40)
//                .forEach(i -> threadPool.submitTask(()->{
//                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" started. ");
//                    try
//                    {
//                        Thread.sleep(10_000);
//                    }
//                    catch(InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" finished");
//                }));

        SimpleThreadPoolV2 threadPool = new SimpleThreadPoolV2();
        IntStream.rangeClosed(0,40)
                .forEach(i -> threadPool.submitTask(()->{
                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" started. ");
                    try
                    {
                        Thread.sleep(1_000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" finished");
                }));

        Thread.sleep(10_000);
        threadPool.shudDownThreadPool();
        threadPool.submitTask (()-> System.out.println("submit after shutdown"));
    }
}
