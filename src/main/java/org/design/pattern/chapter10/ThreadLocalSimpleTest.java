package org.design.pattern.chapter10;

/**
 * projectName: javaconcurrent
 * fileName: ThreadLocalSimpleTestr.java
 * packageName: org.design.pattern.chapter10
 * date: 2020年01月28日  15:06:54
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadLocalSimpleTest
{
    final static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>(){
        @Override
        protected String initialValue()
        {
            return "Hello";
        }
    };

    public static void main(String[] args) throws InterruptedException
    {
//        THREAD_LOCAL.set("ENMUSER");
        Thread.sleep(5_000);
        System.out.println(THREAD_LOCAL.get());
    }
}
