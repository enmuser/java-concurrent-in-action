package org.example.chapter9;

/**
 * projectName: javaconcurrent
 * fileName: ProduceConsumerVersion.java
 * packageName: org.example.chapter9
 * date: 2020年01月21日  20:16:26
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ProduceConsumerVersion
{
    private int i = 1;

    private final Object Lock = new Object();

    public void produce(){
        synchronized(Lock){
            System.out.println("P -> "+ (i++));
        }
    }

    public void consumer(){
        synchronized(Lock){
            System.out.println("C -> "+ i);
        }
    }

    public static void main(String[] args)
    {
        ProduceConsumerVersion version1 = new ProduceConsumerVersion();
        new Thread(()->{
            while(true){
              version1.produce();
            }
        }).start();

        new Thread(()->{
            while(true)
            {
                version1.consumer();
            }
        }).start();
    }
}
