package org.example.chapter9;

import java.util.stream.Stream;

/**
 * projectName: javaconcurrent
 * fileName: ProduceConsumerVersion2.java
 * packageName: org.example.chapter9
 * date: 2020年01月21日  20:21:14
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ProduceConsumerVersion3
{
    private int i = 0;

    private final Object Lock = new Object();

    private volatile boolean isProduce = false;

    public void produce(){
        synchronized(Lock){
            while(isProduce){
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

            i++;
            System.out.println(Thread.currentThread().getName()+" P -> "+i);
            isProduce = true;
            Lock.notifyAll();
            System.out.println(Thread.currentThread().getName()+"==>notifyAll");
        }
    }

    public void consumer(){
        synchronized(Lock){
            while(!isProduce){
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
            System.out.println(Thread.currentThread().getName()+" C -> "+i);
            isProduce = false;
            Lock.notify();
            System.out.println(Thread.currentThread().getName()+"==>notifyAll");
        }
    }

    public static void main(String[] args)
    {
        ProduceConsumerVersion3 version2 = new ProduceConsumerVersion3();

        Stream.of("P1","P2","P3").forEach(n->new Thread(()->{
            while(true){
                version2.produce();
            }
        },n).start());

        Stream.of("C1","C2","C3").forEach(n->new Thread(()->{
            while(true){
                version2.consumer();
            }
        },n).start());
    }
}
