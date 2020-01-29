package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: DisplayClientThread.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:44:23
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class DisplayClientThread extends Thread
{
    private final ActiveObject activeObject;

    public DisplayClientThread(String name,ActiveObject activeObject)
    {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run()
    {
        try {
            for (int i = 0; true; i++) {
                String text = Thread.currentThread().getName() + "=>" + i;
                activeObject.displayString(text);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
