package org.example.chapter9;

/**
 * projectName: javaconcurrent
 * fileName: ProduceConsumerVersion2.java
 * packageName: org.example.chapter9
 * date: 2020年01月21日  20:21:14
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ProduceConsumerVersion2
{
    private int i = 0;

    private final Object Lock = new Object();

    private volatile boolean isProduce = false;

    public void produce(){
        synchronized(Lock){
            if(isProduce){
                try
                {
                    System.out.println(Thread.currentThread().getName()+"==>wait");
                    Lock.wait();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }else{
                i++;
                System.out.println(Thread.currentThread().getName()+" P -> "+i);
                isProduce = true;
                Lock.notify();
                System.out.println(Thread.currentThread().getName()+"==>notify");
            }
        }
    }

    public void consumer(){
        synchronized(Lock){
            if(isProduce){
                System.out.println(Thread.currentThread().getName()+" C -> "+i);
                isProduce = false;
                Lock.notify();
                System.out.println(Thread.currentThread().getName()+"==>notify");
            }else {
                try
                {
                    System.out.println(Thread.currentThread().getName()+"==>wait");
                    Lock.wait();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        ProduceConsumerVersion2 version2 = new ProduceConsumerVersion2();
        new Thread(()->{
            while(true){
                version2.produce();
            }
        },"P1").start();

        new Thread(()->{
            while(true){
                version2.produce();
            }
        },"P2").start();

        new Thread(()->{
            while(true)
            {
                version2.consumer();
            }
        },"C1").start();

        new Thread(()->{
            while(true)
            {
                version2.consumer();
            }
        },"C2").start();
    }


}
