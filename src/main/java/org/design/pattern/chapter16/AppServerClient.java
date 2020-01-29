package org.design.pattern.chapter16;

import java.io.IOException;

/**
 * projectName: javaconcurrent
 * fileName: AppServerClient.java
 * packageName: org.design.pattern.chapter16
 * date: 2020年01月29日  11:01:48
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class AppServerClient
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        AppServer server = new AppServer(13345);
        server.start();
//        Thread.sleep(15_000L);
//        server.shutdown();
    }
}
