package org.design.pattern.chapter15;

import java.util.stream.IntStream;

/**
 * projectName: javaconcurrent
 * fileName: PerThreadClient.java
 * packageName: org.design.pattern.chapter15
 * date: 2020年01月29日  09:29:09
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class PerThreadClient
{
    public static void main(String[] args)
    {
        final MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(1,10).forEach(i -> handler.request(new Message(String.valueOf(i))));
        handler.shutDown();
    }
}
