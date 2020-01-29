package org.design.pattern.chapter18;

/**
 * projectName: javaconcurrent
 * fileName: ActiveObjectClient.java
 * packageName: org.design.pattern.chapter18
 * date: 2020年01月29日  17:51:57
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ActiveObjectClient
{
    public static void main(String[] args)
    {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread(activeObject,"Alex").start();
        new MakerClientThread(activeObject,"ENMUSER").start();

        new DisplayClientThread("Chris",activeObject).start();
    }
}
