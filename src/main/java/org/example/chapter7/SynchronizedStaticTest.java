package org.example.chapter7;

/**
 * projectName: javaconcurrent
 * fileName: SynchronizedStaticTest.java
 * packageName: org.example.chapter7
 * date: 2020年01月21日  19:16:51
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SynchronizedStaticTest
{
    public static void main(String[] args)
    {
        new Thread(()->{
            SynchronizedStatic.M2();
        },"MM2").start();

        new Thread(()->{
            SynchronizedStatic.M1();
        },"MM1").start();


        new Thread(()->{
            SynchronizedStatic.M3();
        },"MM3").start();
    }
}
