package org.design.pattern.chapter16;

import java.util.Random;

/**
 * projectName: javaconcurrent
 * fileName: CounterIncrement.java
 * packageName: org.design.pattern.chapter16
 * date: 2020年01月29日  09:51:21
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CounterIncrement extends Thread
{

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run()
    {
        try
        {
            while(!terminated){
                System.out.println(Thread.currentThread().getName()+" "+counter++);
                Thread.sleep(random.nextInt(1000));
            }
        }
        catch(InterruptedException e)
        {
            //e.printStackTrace();
        }
        finally
        {
            this.clean();
        }
    }

    private void clean()
    {
        System.out.println("do some clean work for the second phase,current counter " + counter);
    }

    public void shutdown(){
        this.terminated = true;
        this.interrupt();
    }
}
