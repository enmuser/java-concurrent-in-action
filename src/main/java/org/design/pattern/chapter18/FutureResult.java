package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: FutureResult.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  16:50:37
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class FutureResult implements Result
{
    private Result result;

    private boolean ready = false;

    public synchronized void setResult(Result result){
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }
    @Override
    public synchronized Object getResultValue()
    {
        while(!ready){
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
