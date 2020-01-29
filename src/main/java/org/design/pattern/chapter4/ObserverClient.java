package org.design.pattern.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: ObserverClient.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  16:10:17
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ObserverClient
{
    public static void main(String[] args)
    {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("==================");
        subject.setState(10);
        System.out.println("==================");
        subject.setState(10);

        System.out.println("==================");
        subject.setState(15);
    }
}
