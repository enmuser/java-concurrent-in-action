package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: ActiveObject.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  16:37:29
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public interface ActiveObject
{
    Result makeString(int count,char fillChar);

    void displayString(String text);
}
