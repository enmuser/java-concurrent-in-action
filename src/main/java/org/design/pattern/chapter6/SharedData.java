package org.design.pattern.chapter6;

import java.nio.CharBuffer;

/**
 * projectName: javaconcurrent
 * fileName: SharedData.java
 * packageName: org.design.pattern.chapter6
 * date: 2020年01月26日  22:24:52
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class SharedData
{
    private final char[] buffer;

    private final ReadWriterLock lock = new ReadWriterLock();

    public SharedData(int size)
    {
        buffer = new char[size];
        for(int i = 0; i < size ; i++)
        {
            buffer[i] = '*';
        }
    }

    public char[] readBuffer() throws InterruptedException
    {
        try
        {
            lock.readLock();
            return doRead();
        }
        finally
        {
            lock.readUnLock();
        }
    }

    private char[] doRead()
    {
        char[] newBuffer = new char[buffer.length];
        for(int i = 0; i < buffer.length; i++)
        {
            newBuffer[i] = buffer[i];
        }
        slowly(50);
        return newBuffer;
    }

    private void slowly(int mills)
    {
        try
        {
            Thread.sleep(mills);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void writeBuffer(char c) throws InterruptedException
    {
        try
        {
            lock.writeLock();
            this.doWrite(c);
        }
        finally
        {
            lock.writeUnLock();
        }
    }

    private void doWrite(char c)
    {
        for(int i = 0; i < buffer.length ; i++)
        {
            buffer[i] = c;
            slowly(10);
        }

    }
}
