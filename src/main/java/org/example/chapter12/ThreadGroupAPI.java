package org.example.chapter12;

/**
 * projectName: javaconcurrent
 * fileName: ThreadGroupAPI.java
 * packageName: org.example.chapter12
 * date: 2020年01月23日  10:04:41
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadGroupAPI
{
    public static void main(String[] args) throws InterruptedException
    {
        ThreadGroup group1 = new ThreadGroup("TG1");

        Thread thread = new Thread(group1,"t1"){
            @Override
            public void run()
            {
//                while(true){
                    try
                    {
                        Thread.sleep(1_000);
                    }
                    catch(InterruptedException e)
                    {
                        System.out.println("thread is Interrupt!" );
                        e.printStackTrace();
//                        break;
                    }
//                }
            }
        };

//        group1.setDaemon(true);
        thread.start();
        Thread.sleep(2_000);
        System.out.println(group1.isDestroyed());
        group1.destroy();
        System.out.println(group1.isDestroyed());

        ThreadGroup group2 = new ThreadGroup(group1,"TG2");

        Thread thread2 = new Thread(group2,"t1"){
            @Override
            public void run()
            {
                while(true){
                    try
                    {
                        Thread.sleep(10_000);
                    }
                    catch(InterruptedException e)
                    {
                        System.out.println("thread2 is Interrupt!" );
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        thread2.start();

        group1.interrupt();
    }
}
