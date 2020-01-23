package org.example;

/**
 * projectName: javaconcurrent
 * fileName: TempleteMethod.java
 * packageName: org.example
 * date: 2020年01月20日  18:28:14
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TempleteMethod
{
    public final void printMessage(String message){
        System.out.println("###############");
        insertMessage(message);
        System.out.println("###############");
    }

    protected void insertMessage(String message)
    {

    }

    public static void main(String[] args)
    {
        TempleteMethod method1 = new TempleteMethod(){
            @Override
            protected void insertMessage(String message)
            {
                System.out.println("*"+message+"*");
            }
        };
        method1.printMessage("Hello Template!");

        TempleteMethod method2 = new TempleteMethod(){
            @Override
            protected void insertMessage(String message)
            {
                System.out.println("@"+message+"@");
            }
        };
        method2.printMessage("Hello Template!");
    }

}
