package org.example.chapter3;

/**
 * projectName: javaconcurrent
 * fileName: CreateThread.java
 * packageName: org.example.chapter3
 * date: 2020年01月20日  22:34:17
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CreateThread
{
    /**
     * java.lang.StackOverflowError
     * JVM 虚拟机栈 被充爆
     */
    private static int counter = 0;

    public static void main(String[] args)
    {
        try
        {
            add(0);
        } catch(Error e){
            e.printStackTrace();
            System.out.println(counter);
        }

    }

    private static void add(int index){
        ++counter;
        add(index+1);
    }
}
