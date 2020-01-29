package org.design.pattern.chapter6;

/**
 * projectName: javaconcurrent
 * fileName: ReadWriterLock.java
 * packageName: org.design.pattern.chapter6
 * date: 2020年01月26日  22:11:01
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ReadWriterLock
{
    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;

    private final boolean preferWrite;

    public ReadWriterLock()
    {
        this(true);
    }

    public ReadWriterLock(boolean preferWrite)
    {
        this.preferWrite = preferWrite;
    }

    public synchronized void readLock() throws InterruptedException
    {
        this.waitingReaders++;
        try
        {
            while(writingWriters>0 || (preferWrite && waitingWriters > 0)){
                this.wait();
            }
            this.readingReaders++;
        }
        finally
        {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnLock(){
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException
    {
        this.waitingWriters++;
        try
        {
            while(this.readingReaders > 0 || this.writingWriters>0){
                this.wait();
            }
            this.writingWriters++;
        }
        finally
        {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnLock(){
        this.writingWriters--;
        this.notifyAll();
    }
}
