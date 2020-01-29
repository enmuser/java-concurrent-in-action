package org.design.pattern.chapter3;

/**
 * projectName: javaconcurrent
 * fileName: VolatileTest2.java
 * packageName: org.design.pattern.chapter3
 * date: 2020年01月24日  00:16:39
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class VolatileTest2
{
    private static volatile int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 30;

    public static void main(String[] args)
    {
        new Thread(()->{
            while(INIT_VALUE < MAX_LIMIT){
                System.out.println("THREAD-ADD-T1T===>"+(++INIT_VALUE));
                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        },"THREAD-ADD-1").start();

        new Thread(()->{
            while(INIT_VALUE < MAX_LIMIT){
                System.out.println("THREAD-ADD-T2T===>"+(++INIT_VALUE));
                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        },"THREAD-ADD-2").start();
    }
}
