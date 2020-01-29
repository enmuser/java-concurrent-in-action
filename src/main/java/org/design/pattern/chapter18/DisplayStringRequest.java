package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: DisplayStringRequest.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:04:18
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DisplayStringRequest extends MethodRequest
{
    private final String text;

    public DisplayStringRequest(Servant servant,String text)
    {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute()
    {
        servant.displayString(text);
    }
}
