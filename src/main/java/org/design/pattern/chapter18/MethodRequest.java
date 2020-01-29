package org.design.pattern.chapter18;

import org.design.pattern.chapter8.Future;

/**
 * projectName: javaconcurrent
 * fileName: MethodRequest.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  16:49:41
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public abstract class MethodRequest
{
    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult)
    {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
