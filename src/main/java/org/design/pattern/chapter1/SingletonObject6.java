package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectTwo.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:53:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject6
{
    private SingletonObject6(){
    }

    private static class InstanceHolder{
        private static final SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }
}
