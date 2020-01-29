package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectTwo.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:53:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject3
{
    private static SingletonObject3 instance;

    private SingletonObject3(){

    }

    // while the problem in multi-thread condition possibly more than one SingleObjectTwo created
    // is resolved, synchronized will undermine the efficiency of code execution
    public synchronized static SingletonObject3 getInstance(){
        if(null == instance)
            instance = new SingletonObject3();
        return SingletonObject3.instance;
    }
}
