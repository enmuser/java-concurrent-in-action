package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: MakeStringRequest.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:00:30
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class MakeStringRequest extends MethodRequest
{
    private final int count;

    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar)
    {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute()
    {
        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);
    }
}
