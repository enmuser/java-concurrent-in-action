package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: Servant.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  16:42:01
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Servant implements ActiveObject
{
    @Override
    public Result makeString(int count, char fillChar)
    {
        char[] buf = new char[count];
        for(int i = 0; i < count; i++)
        {
            buf[i] = fillChar;
            try {
                Thread.sleep(10);
            } catch (Exception e) {

            }
        }

        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text)
    {
        try {
            System.out.println("Display:" + text);
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
