package org.design.pattern.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: OctalObserver.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  16:09:02
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class OctalObserver extends Observer
{
    public OctalObserver(Subject subject)
    {
        super(subject);
    }

    @Override
    public void update()
    {
        System.out.println("Octal String:" + Integer.toOctalString(subject.getState()));
    }
}
