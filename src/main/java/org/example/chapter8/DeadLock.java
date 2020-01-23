package org.example.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: DeadLock.java
 * packageName: org.example.chapter8
 * date: 2020年01月21日  19:38:00
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DeadLock
{
    private final Object lock = new Object();

    private OtherService otherService;

    public DeadLock(OtherService otherService)
    {
        this.otherService = otherService;
    }

    public void M1(){
        synchronized(lock){
            System.out.println("M1==============>");
            otherService.s1();
        }
    }

    public void M2(){
        synchronized(lock){
            System.out.println("M2==============>");
        }
    }
}
