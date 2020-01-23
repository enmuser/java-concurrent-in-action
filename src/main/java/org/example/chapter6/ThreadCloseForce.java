package org.example.chapter6;

/**
 * projectName: javaconcurrent
 * fileName: ThreadCloseForce.java
 * packageName: org.example.chapter6
 * date: 2020年01月21日  15:14:55
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadCloseForce
{
    public static void main(String[] args)
    {
        ThreadService service = new ThreadService();
        long startTime = System.currentTimeMillis();
        service.execute(()->{
//            while(true){
//
//            }
            try
            {
                Thread.sleep(5_000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        service.shutDown(10_000);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
