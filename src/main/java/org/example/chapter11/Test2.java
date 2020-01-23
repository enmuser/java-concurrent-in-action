package org.example.chapter11;

import java.util.Arrays;
import java.util.Optional;

/**
 * projectName: javaconcurrent
 * fileName: Test2.java
 * packageName: org.example.chapter11
 * date: 2020年01月23日  09:27:47
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Test2
{
    public void test(){
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter((e) -> !e.isNativeMethod())
                .forEach(e-> Optional.of(e.getClassName()+" : "+e.getMethodName()+" : "+e.getLineNumber()).ifPresent(System.out::println));

    }
}
