package org.design.pattern.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: TaskClient.java
 * packageName: org.design.pattern.chapter8
 * date: 2020年01月28日  12:03:11
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */

/**
 * Future -> 代表的是为了的一个凭据
 * FutureTask -> 将你的调用逻辑进行隔离
 * FutureService -> 桥接Future 和 FutureTask
 */
public class TaskClient
{
    public static void main(String[] args) throws InterruptedException
    {
        FutureService futureService = new FutureService();
//        Future<String> future = futureService.submit(()->{
//            try
//            {
//                Thread.sleep(10_000);
//            }
//            catch(InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//            return "FINISH";
//        });

        Future<String> future = futureService.submit(()->{
            try
            {
                Thread.sleep(10_000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);
        System.out.println("==============================");
        System.out.println(" do other thing ");
        Thread.sleep(1000);
        System.out.println("==============================");
        //System.out.println(future.get());
        //System.out.println("==============================");
    }
}
