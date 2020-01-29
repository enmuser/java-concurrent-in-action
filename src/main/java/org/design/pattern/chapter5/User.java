package org.design.pattern.chapter5;

/**
 * projectName: javaconcurrent
 * fileName: User.java
 * packageName: org.design.pattern.chapter5
 * date: 2020年01月26日  21:42:44
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class User extends Thread
{
    private final String myName;
    private final String myAddress;
    private final Gate gate;

    public User(String myName, String myAddress, Gate gate)
    {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run()
    {
        System.out.println(myName + " BEGIN");
        while (true) {
            this.gate.pass(myName, myAddress);
        }
    }
}
