package org.design.pattern.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: CustomCountDownTest.java
 * packageName: org.design.pattern.chapter14
 * date: 2020年01月28日  22:50:09
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CustomCountDownTest
{
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final CountDown latch = new CountDown(5);
        System.out.println("准备多线程处理任务.");
        //The first phase.

        IntStream.rangeClosed(1, 5).forEach(i ->
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " is working.");
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }, String.valueOf(i)).start()
        );

        latch.await();
        //The second phase.
        System.out.println("多线程任务全部结束,准备第二阶段任务");
        System.out.println("............");
        System.out.println("FINISH");

    }
}
