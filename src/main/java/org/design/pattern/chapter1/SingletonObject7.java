package org.design.pattern.chapter1;

/**
 * projectName: javaconcurrent
 * fileName: SingleObjectTwo.java
 * packageName: org.design.pattern.chapter1
 * date: 2020年01月23日  20:53:38
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SingletonObject7
{
    private SingletonObject7(){
    }

    private enum Singleton{
        INSTANCE;
        private final SingletonObject7 instance;

        Singleton(){
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

}
