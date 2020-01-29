package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectTwo.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:53:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject4
{
    private static SingletonObject4 instance;

    private SingletonObject4(){

    }

    // while the problem in multi-thread condition possibly more than one SingleObjectTwo created
    // is resolved, synchronized will undermine the efficiency of code execution
    // we use the double check resolve problems above
    //however,in this condition,may be produce NullPointerException
    // at the case of happens-before in JVM
    // JVM may be make some improve by change the sequence of code execution
    public static SingletonObject4 getInstance(){
        if(null == instance){
            synchronized(SingletonObject4.class)
            {
                if(null == instance)
                instance = new SingletonObject4();
            }
        }
        return SingletonObject4.instance;
    }
}
