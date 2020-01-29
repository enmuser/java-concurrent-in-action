package org.design.pattern.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: FutureTask.java
 * packageName: org.design.pattern.chapter8
 * date: 2020年01月28日  11:27:10
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public interface FutureTask<T>
{
    T call();
}
