package org.design.pattern.chapter3;

/**
 * projectName: javaconcurrent
 * fileName: VoletileTest.java
 * packageName: org.design.pattern.chapter3
 * date: 2020年01月23日  23:45:37
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class VolatileTest
{
    private static volatile int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 10;

    public static void main(String[] args)
    {
        new Thread(()->{
            int localValue = INIT_VALUE;
            while(localValue < MAX_LIMIT){
                if(localValue != INIT_VALUE){
                    System.out.printf("Reader:The value updated to [%d]\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"Reader").start();

        new Thread(()->{
            int localValue = INIT_VALUE;
            while(localValue < MAX_LIMIT){
                   System.out.printf("Update:the value update to [%d]\n",++localValue);
                   INIT_VALUE = localValue;
//                try
//                {
//                    Thread.sleep(1);
//                }
//                catch(InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
            }
        },"Update").start();
    }
}
