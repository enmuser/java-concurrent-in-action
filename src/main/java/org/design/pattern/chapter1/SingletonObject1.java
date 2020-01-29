package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectOne.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:45:37
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject1
{

     //can't lazy load
    private static final SingletonObject1 SINGLE_OBJECT_ONE = new SingletonObject1();

    private SingletonObject1(){
        //empty
    }
    public static SingletonObject1 getInstance(){
        return SINGLE_OBJECT_ONE;
    }
}
