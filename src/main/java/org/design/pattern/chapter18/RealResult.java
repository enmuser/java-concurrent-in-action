package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: RealResult.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  16:43:43
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class RealResult implements Result
{
    private final Object resultValue;

    public RealResult(Object resultValue)
    {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue()
    {
        return this.resultValue;
    }
}
