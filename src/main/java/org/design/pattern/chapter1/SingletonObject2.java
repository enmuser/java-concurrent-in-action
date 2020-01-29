package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectTwo.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:53:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject2
{
    private static SingletonObject2 instance;

    private SingletonObject2(){

    }

    // in multi-thread condition possibly more than one SingleObjectTwo created
    public static SingletonObject2 getInstance(){
        if(instance == null)
            instance = new SingletonObject2();
        return SingletonObject2.instance;
    }
}
