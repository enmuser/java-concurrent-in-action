package org.design.pattern.chapter5;

/**
 * projectName: javaconcurrent
 * fileName: Gate.java
 * packageName: org.design.pattern.chapter5
 * date: 2020年01月26日  21:31:49
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Gate
{
    private int counter = 0;
    private String name =  "Nobody";
    private String address = "Nowhere";

    public synchronized void pass(String name,String address){
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify()
    {
        if(this.name.charAt(0) != this.name.charAt(0)){
            System.out.println("============BROKEN============\n"+toString());
        }
    }

    @Override
    public synchronized String toString()
    {
        return "No." + counter + ":" + name + "," + address;
    }
}
