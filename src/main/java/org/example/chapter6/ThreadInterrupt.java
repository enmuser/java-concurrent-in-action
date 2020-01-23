package org.example.chapter6;

/**
 * projectName: javaconcurrent
 * fileName: ThreadInterrupt.java
 * packageName: org.example.chapter6
 * date: 2020年01月21日  13:20:35
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadInterrupt
{
    public static void main(String[] args) throws InterruptedException
    {
//        Thread thread = new Thread(()->{
//            while(true){
//                try
//                {
//                    Thread.sleep(10_000);
//                }
//                catch(InterruptedException e)
//                {
//                    e.printStackTrace();
//                    System.out.println(Thread.interrupted());
//
//                }
//            }
//        });
//
//        thread.start();
//        Thread.sleep(1000);
//        System.out.println(thread.isInterrupted());
////        System.out.println("Interrupt Before");
//        thread.interrupt();
////        System.out.println("Interrupt After");
//        System.out.println(thread.isInterrupted());
////        System.out.println("Interrupt After2");

        final Object obj  = new Object();
        Thread thread = new Thread(()->{
            while(true){
                try
                {
                    synchronized(obj){
                        obj.wait();
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                    System.out.println(Thread.interrupted());

                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());
//        System.out.println("Interrupt Before");
        thread.interrupt();
//        System.out.println("Interrupt After");
        System.out.println(thread.isInterrupted());
//        System.out.println("Interrupt After2");
    }
}
