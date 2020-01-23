package org.example.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
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
public class SimpleThreadPoolV3 extends Thread
{
    private int size;

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

    private int min;

    private int max;

    private int active;

    public SimpleThreadPoolV3()
    {
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARDPOLICY);
    }

    public SimpleThreadPoolV3( int min,int active,int max,int taskQueueSize, DiscardPolicy discardPolicy)
    {
        this.min = min;
        this.max = max;
        this.active = active;
        this.taskQueueSize = taskQueueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init(){
        for(int i = 0; i < this.min; i++)
        {
            createWorkerTask();
        }
        this.size = this.min;
        this.start();
    }

    @Override
    public void run()
    {
        while(!destory){
            System.out.printf("POOL#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                          this.min,this.active,this.max,this.size,TASK_QUEUE.size());
            try
            {
                Thread.sleep(5_000L);
                if(TASK_QUEUE.size() > active && size < active){
                    for(int i = size; i < active;i++){
                        createWorkerTask();
                    }
                    System.out.println("The workerThread of POOL is increased to activesize");
                    this.size = this.active;
                }else if(TASK_QUEUE.size() > max && size < max){
                    for(int i = size; i < max;i++){
                        createWorkerTask();
                    }
                    System.out.println("The workerThread of POOL is increased to maxsize");
                    this.size = this.max;
                }

                if(TASK_QUEUE.isEmpty() && size > active){
                    System.out.println("Starting to reduce workerThread");
                    int releaseSize = size - active;
                    synchronized(WORKER_THREADS){

                        Iterator<WorkerThread> it = WORKER_THREADS.iterator();
                        while(it.hasNext()){
                            if(releaseSize <= 0)
                                break;
                            WorkerThread task = it.next();
                            if(task.taskState == TaskState.RUNNING){
                                continue;
                            }
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        this.size = active+releaseSize;
                    }
                }
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
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
        synchronized(WORKER_THREADS){
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

    public int getMin()
    {
        return min;
    }

    public int getMax()
    {
        return max;
    }

    public int getActive()
    {
        return active;
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
                            System.out.println(Thread.currentThread().getName()+"<==>The WorkThread is Closed!");
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

        SimpleThreadPoolV3 threadPool = new SimpleThreadPoolV3();
        IntStream.rangeClosed(0,40)
                .forEach(i -> threadPool.submitTask(()->{
                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" started. ");
                    try
                    {
                        Thread.sleep(5_000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("The Runnable "+i+" be serviced by "+Thread.currentThread()+" finished");
                }));

        Thread.sleep(10_000);
        threadPool.shudDownThreadPool();
//        threadPool.submitTask (()-> System.out.println("submit after shutdown"));
    }
}
