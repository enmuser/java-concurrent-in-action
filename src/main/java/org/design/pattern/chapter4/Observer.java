package org.design.pattern.chapter4;

/**
 * projectName: javaconcurrent
 * fileName: Observer.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  15:33:23
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public abstract class Observer
{
    protected Subject subject;

    public Observer(Subject subject)
    {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();
}
