package org.example.chapter3;

/**
 * projectName: javaconcurrent
 * fileName: CreateThread2.java
 * packageName: org.example.chapter3
 * date: 2020年01月20日  22:43:53
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CreateThread2
{
    private static int counter = 0;
    public static void main(String[] args)
    {
        Thread thread = new Thread(null, new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    add(0);
                }
                catch(Error e)
                {
//                    e.printStackTrace();
                    System.out.println(counter);
                }
            }
            private void add(int i){
                ++counter;
                add(i+1);
            }
        },"Test",1<<24);
        thread.start();

    }
}
