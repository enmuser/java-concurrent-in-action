package org.example.chapter5;

import org.example.chapter4.ThreadSimpleAPI;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: ThreadJoin2.java
 * packageName: org.example.chapter5
 * date: 2020年01月21日  11:05:56
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadJoin2
{
    public static void main(String[] args) throws InterruptedException
    {
//        Thread thread = new Thread(()->{
//            try
//            {
//                System.out.println("thread is running");
//                Thread.sleep(10_000);
//                System.out.println("thread is done");
//            }
//            catch(InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        });
//        thread.start();
//        thread.join(5_000);
//
//        Optional.of("All thread joined is running completely").ifPresent(System.out::println);
//        IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"==>"+i));

        Thread.currentThread().join();
    }

}
