package org.design.pattern.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: WaitSet.java
 * packageName: org.design.pattern.chapter2
 * date: 2020年01月23日  22:44:23
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class WaitSet
{
    private static final Object LOCK = new Object();

    public static void work(){
        synchronized(LOCK){
            System.out.println("Begin....");
            try
            {
                System.out.println("Thread will coming...");
                LOCK.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Thread will out...");
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        /**
         * 1.所有对象都会有一个waitset 用来存放调用该对象wait方法之后的进入的block状态的线程
         * 2.线程被notify之后不一定会立即执行
         * 3.线程从wait set中被唤醒的顺序不一定是FIFO的
         * 4.线程被唤醒后，必须重新获取锁
         */
        new Thread(){
            @Override
            public void run()
            {
                work();
            }
        }.start();

        Thread.sleep(5_000);

        synchronized(LOCK){
            LOCK.notify();
        }
//        IntStream.rangeClosed(1,10).forEach(i->{
//            new Thread((String.valueOf("THREAD-LOCK-"+i))){
//                @Override
//                public void run()
//                {
//                    synchronized(LOCK){
//                        try
//                        {
//                            Optional.of(Thread.currentThread().getName()+" will come to wait set.")
//                                    .ifPresent(System.out::println);
//                            LOCK.wait();
//                            Optional.of(Thread.currentThread().getName()+" will leave to wait set.")
//                                    .ifPresent(System.out::println);
//                        }
//                        catch(InterruptedException e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//        });
//
//        Thread.sleep(3000);
//
//        IntStream.rangeClosed(1,10).forEach(i->{
//            synchronized(LOCK){
//                LOCK.notify();
//                try
//                {
//                    Thread.sleep(1000);
//                }
//                catch(InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
