package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: MakerClientThread.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:47:22
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class MakerClientThread extends Thread
{
    private final ActiveObject activeObject;
    private final char fillChar;

    public MakerClientThread(ActiveObject activeObject, String name) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for(int i = 0;true;i++){
                Result result = activeObject.makeString(i + 1, fillChar);
                Thread.sleep(20);
                String value = (String) result.getResultValue();
                System.out.println(Thread.currentThread().getName()+": value="+value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
