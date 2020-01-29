package org.design.pattern.chapter16;

/**
 * projectName: javaconcurrent
 * fileName: CounterTest.java
 * packageName: org.design.pattern.chapter16
 * date: 2020年01月29日  09:51:04
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CounterTest
{
    public static void main(String[] args) throws InterruptedException
    {
        final CounterIncrement increment = new CounterIncrement();
        increment.start();


        Thread.sleep(10_000L);
        increment.shutdown();
    }

}
