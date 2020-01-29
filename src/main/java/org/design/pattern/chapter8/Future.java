package org.design.pattern.chapter8;

/**
 * projectName: javaconcurrent
 * fileName: Future.java
 * packageName: org.design.pattern.chapter8
 * date: 2020年01月28日  11:24:17
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public interface Future<T>
{
    T get() throws InterruptedException;
}
