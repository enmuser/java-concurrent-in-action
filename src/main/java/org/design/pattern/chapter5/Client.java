package org.design.pattern.chapter5;

/**
 * projectName: javaconcurrent
 * fileName: Client.java
 * packageName: org.design.pattern.chapter5
 * date: 2020年01月26日  21:45:03
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Client
{
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("Baobao", "Beijing", gate);
        User sh = new User("ShangLao", "ShangHai", gate);
        User gz = new User("GuangLao", "GuangZhou", gate);

        bj.start();
        sh.start();
        gz.start();
    }
}
