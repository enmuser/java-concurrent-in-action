package org.design.pattern.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * projectName: javaconcurrent
 * fileName: MessageHandler.java
 * packageName: org.design.pattern.chapter15
 * date: 2020年01月29日  09:24:36
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class MessageHandler
{
    private final static Random random = new Random(System.currentTimeMillis());

    private final static Executor executor = Executors.newFixedThreadPool(5);
    public void request(Message message){
        executor.execute(()->{
            String value = message.getMessage();
            try
            {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });
//        new Thread(()->{
//            String value = message.getMessage();
//            try
//            {
//                Thread.sleep(random.nextInt(1000));
//                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
//            }
//            catch(InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }).start();
    }

    public void shutDown(){
        ((ExecutorService) executor).shutdown();
    }
}
