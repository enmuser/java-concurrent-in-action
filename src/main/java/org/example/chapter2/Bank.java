package org.example.chapter2;

/**
 * projectName: javaconcurrent
 * fileName: Bank.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  19:11:54
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Bank
{
    public static void main(String[] args)
    {
        TicketWindow ticketWindow1 = new TicketWindow("一号柜台");
        TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
        TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
        TicketWindow ticketWindow4 = new TicketWindow("四号柜台");

        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
        ticketWindow4.start();
    }
}
