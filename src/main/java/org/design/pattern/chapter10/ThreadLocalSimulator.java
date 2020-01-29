package org.design.pattern.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * projectName: javaconcurrent
 * fileName: ThreadLocalSimulator.java
 * packageName: org.design.pattern.chapter10
 * date: 2020年01月28日  18:04:06
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ThreadLocalSimulator<T>
{
    private final Map<Thread,T> storage = new HashMap<>();

    public void set(T t){
        synchronized(this){
            Thread key = Thread.currentThread();
            storage.put(key,t);
        }
    }

    public T get(){
        synchronized(this){
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if(value == null){
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue()
    {
        return null;
    }
}
