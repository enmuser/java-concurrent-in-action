package org.example;

/**
 * projectName: javaconcurrent
 * fileName: TryConcurrent.java
 * packageName: org.example
 * date: 2020年01月20日  15:08:53
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TryConcurrent
{
    public static void main(String[] args)
    {

        Thread th1 = new Thread(){
            @Override
            public void run()
            {
                for(int i = 0; i < 50; i++)
                {
                    System.out.println("This is Thread-1=="+i);
                }
            }
        };
        th1.start();
        for(int i = 0; i < 200; i++)
        {
            System.out.println("This is Thread-2=="+i);
        }

//        Runnable run = new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                System.out.println("Runnable");
//            }
//        };
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 20; i++)
                {
                    System.out.println("Runnable");
                }

            }
        },"Runnable-Thread").start();
//        try
//        {
//            Thread.sleep(1000*300L);
//        }
//        catch(InterruptedException e)
//        {
//            e.printStackTrace();
//        }
    }

}
