package org.example.chapter3;

/**
 * projectName: javaconcurrent
 * fileName: CreateThread3.java
 * packageName: org.example.chapter3
 * date: 2020年01月20日  23:14:28
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CreateThread3
{
    private static int counter = 0;
    public static void main(String[] args)
    {
        try
        {
            for(int i = 0; i < Integer.MAX_VALUE; i++)
            {
                ++counter;
                new Thread(()->{
                    byte[] bytes = new byte[1024*1024*2];
                    while(true){

                    }
                }).start();
            }
        }
        catch(Error e)
        {
            System.out.println("Total created Thread nums ==>" + counter);
        }

    }
}
