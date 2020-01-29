package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectTwo.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:53:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject5
{
    //add volatile modified forbidden JVM Change Sequence
    // 不弄保证原则性
    // 可以保证内存的可见性，多个线程看到的是同一份内容
    // 可以保证有序性 保证执行完成
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){

    }

    // while the problem in multi-thread condition possibly more than one SingleObjectTwo created
    // is resolved, synchronized will undermine the efficiency of code execution
    // we use the double check resolve problems above
    //however,in this condition,may be produce NullPointerException
    // at the case of happens-before in JVM
    // JVM may be make some improve by change the sequence of code execution
    public static SingletonObject5 getInstance(){
        if(null == instance){
            synchronized(SingletonObject5.class)
            {
                if(null == instance)
                instance = new SingletonObject5();
            }
        }
        return SingletonObject5.instance;
    }
}
