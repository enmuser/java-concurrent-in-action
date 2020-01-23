package org.example.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: DeadTest.java
 * packageName: org.example.chapter8
 * date: 2020年01月21日  19:45:30
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DeadTest
{


    public static void main(String[] args)
    {

        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(){
            @Override
            public void run()
            {
                while(true)
                {
                    deadLock.M1();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run()
            {
                while(true){
                  otherService.s2();
                }
            }
        }.start();

    }
}
