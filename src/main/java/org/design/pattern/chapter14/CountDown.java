package org.design.pattern.chapter14;

/**
 * projectName: javaconcurrent
 * fileName: CountDown.java
 * packageName: org.design.pattern.chapter14
 * date: 2020年01月28日  22:44:19
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CountDown
{
    private final int total;

    private int counter = 0;

    public CountDown(int total)
    {
        this.total = total;
    }

    public void countDown(){
        synchronized(this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException
    {
        synchronized(this){
            while(this.counter != total){
                this.wait();
            }
        }
    }
}
