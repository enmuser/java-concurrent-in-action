package org.design.pattern.chapter17;

/**
 * projectName: javaconcurrent
 * fileName: Request.java
 * packageName: org.design.pattern.chapter17
 * date: 2020年01月29日  11:43:07
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Request
{
    private final String name;

    private final int id;

    public Request(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString()
    {
        return "Request=> No. "+ id + ", Name. " + name;
    }
}
