package org.example.chapter7;

/**
 * projectName: javaconcurrent
 * fileName: SynchronizedRunnable.java
 * packageName: org.example.chapter7
 * date: 2020年01月21日  17:58:44
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SynchronizedRunnable implements Runnable
{
    private static int index = 1;

    private static final int MAX = 500;

    private final Object object = new Object();

    @Override
    public synchronized void run()
    {
        System.out.println(Thread.currentThread().getName()+"======================>");
        while(true){
//            synchronized(object)
//            {
                if(index > MAX) break;
                try
                {
                    Thread.sleep(5);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("柜台：" + Thread.currentThread().getName() + " 叫到的号码 " + (index++));
//            }
        }
    }

//    private synchronized boolean ticket(){
//        //synchronized 保护共享数据
//        //index getField 读数据
//        if(index > MAX) return true;
//        try
//        {
//            Thread.sleep(5);
//        }
//        catch(InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//        // index = index + 1;
//        //1 getField
//        //2 index 修改 + 1
//        //3 putField index
//        System.out.println("柜台：" + Thread.currentThread().getName() + " 叫到的号码 " + (index++));
//        return false;
//    }

}
