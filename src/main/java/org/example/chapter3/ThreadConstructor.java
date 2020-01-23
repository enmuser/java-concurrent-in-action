package org.example.chapter3;

import java.util.Arrays;

/**
 * projectName: javaconcurrent
 * fileName: ThreadConstructor.java
 * packageName: org.example.chapter3
 * date: 2020年01月20日  21:30:35
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadConstructor
{
    public static void main(String[] args)
    {
        Thread t = new Thread(){
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        int count = t.getThreadGroup().activeCount();

        Thread[] threads = new Thread[count];
        t.getThreadGroup().enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);

    }
}
