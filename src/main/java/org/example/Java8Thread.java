package org.example;

/**
 * projectName: javaconcurrent
 * fileName: Java8Thread.java
 * packageName: org.example
 * date: 2020年01月20日  18:58:22
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Java8Thread
{
    public static void main(String[] args)
    {
        Runnable run = () -> {
            for(int i = 0; i < 10; i++)
            {
                System.out.println("Thread--"+i);
            }

        };
        new Thread(run).start();
    }
}
