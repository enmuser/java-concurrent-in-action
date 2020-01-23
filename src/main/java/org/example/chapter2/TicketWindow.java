package org.example.chapter2;

/**
 * projectName: javaconcurrent
 * fileName: TicketWindow.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  19:12:14
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TicketWindow extends Thread
{
    private String name;

    private static final int Max_Value = 100;

    private static int index = 1;

    public TicketWindow(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        while(index <= Max_Value){
          System.out.println("柜台："+name+" 叫到的号码 "+index++);
        }
    }
}
