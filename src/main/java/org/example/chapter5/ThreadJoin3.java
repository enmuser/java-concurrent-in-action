package org.example.chapter5;

/**
 * projectName: javaconcurrent
 * fileName: ThreadJoin3.java
 * packageName: org.example.chapter5
 * date: 2020年01月21日  11:22:09
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadJoin3
{
    public static void main(String[] args) throws InterruptedException
    {
        long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(new Capturable("M1",1_000L));
        Thread thread2 = new Thread(new Capturable("M2",3_000L));
        Thread thread3 = new Thread(new Capturable("M3",2_000L));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        long endTime = System.currentTimeMillis();

        System.out.printf("Save data begin timeStamp is : %s ,end timeStamp is : %s",startTime,endTime);
    }



}

class Capturable implements Runnable{

    private final String name;

    private final long time;

    public Capturable(String name, long time)
    {
        this.name = name;
        this.time = time;
    }

    @Override
    public void run()
    {
        try
        {

            Thread.sleep(time);
            long threadstartTime = System.currentTimeMillis();
            System.out.printf(name+" captures completely and start to save data at %s",threadstartTime);
            System.out.println();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
