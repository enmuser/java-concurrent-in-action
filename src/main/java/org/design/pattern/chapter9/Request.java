package org.design.pattern.chapter9;

/**
 * projectName: javaconcurrent
 * fileName: Request.java
 * packageName: org.design.pattern.chapter9
 * date: 2020年01月28日  13:13:20
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Request
{
    final private String value;

    public Request(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
