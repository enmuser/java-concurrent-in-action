package org.example.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: OtherService.java
 * packageName: org.example.chapter8
 * date: 2020年01月21日  19:38:17
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class OtherService
{
    private final Object lock = new Object();

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock)
    {
        this.deadLock = deadLock;
    }

    public void s1(){
         synchronized(lock){
             System.out.println("s1============>");
         }
    }

    public void s2(){
        synchronized(lock){
            System.out.println("s2==============>");
            deadLock.M2();
        }
    }
}
