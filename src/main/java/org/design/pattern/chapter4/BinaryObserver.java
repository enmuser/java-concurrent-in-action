package org.design.pattern.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: BinaryObserver.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  16:07:05
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class BinaryObserver extends Observer
{
    public BinaryObserver(Subject subject)
    {
        super(subject);
    }

    @Override
    public void update()
    {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
