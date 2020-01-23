package org.example.chapter12;

/**
 * projectName: javaconcurrent
 * fileName: ThreadGroup.java
 * packageName: org.example.chapter12
 * date: 2020年01月23日  09:51:02
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadGroupCreate
{
    public static void main(String[] args)
    {
        ThreadGroup group1 = new ThreadGroup("TG1");

        Thread thread = new Thread(group1,"t1"){
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
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

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
                        e.printStackTrace();
                    }
                }
            }
        };
        thread2.start();
    }
}
