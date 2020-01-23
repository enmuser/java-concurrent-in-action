package org.example.chapter4;

import java.util.Optional;

/**
 * projectName: javaconcurrent
 * fileName: ThreadSimpleAPI.java
 * packageName: org.example.chapter4
 * date: 2020年01月21日  00:23:18
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadSimpleAPI
{
    public static void main(String[] args)
    {
        Thread thread = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try
            {
                Thread.sleep(100_000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

        },"t1");
        thread.start();
        Optional.of(thread.getName()).ifPresent(System.out::println);
        Optional.of(thread.getId()).ifPresent(System.out::println);
        Optional.of(thread.getPriority()).ifPresent(System.out::println);
    }
}
