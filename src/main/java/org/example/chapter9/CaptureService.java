package org.example.chapter9;

import java.util.*;

/**
 * projectName: javaconcurrent
 * fileName: CaptureService.java
 * packageName: org.example.chapter9
 * date: 2020年01月22日  20:17:44
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class CaptureService
{
    private static final LinkedList<Control> CONTROLS = new LinkedList<>();

    private static final int MAX = 5;

    public static void main(String[] args)
    {
        List<Thread>  worker = new ArrayList<>();
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });
        worker.stream().forEach(t ->
        {
            try
            {
                t.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    public static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The worker thread [ "+ Thread.currentThread().getName() +" ] BEGIN capture data")
            .ifPresent(System.out::println);
            synchronized(CONTROLS){
                while(CONTROLS.size() > MAX){
                    try
                    {
                        CONTROLS.wait();
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            Optional.of("The worker thread [ "+ Thread.currentThread().getName() +" ] is working ...")
                    .ifPresent(System.out::println);
            try
            {
                Thread.sleep(10_000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            synchronized(CONTROLS){
                Optional.of("The worker thread [ "+ Thread.currentThread().getName() +" ] END capture data")
                        .ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }


    private static class Control{

    }
}


