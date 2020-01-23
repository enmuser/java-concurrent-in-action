package org.example.chapter2;

import java.io.IOException;
import java.util.Properties;

/**
 * projectName: javaconcurrent
 * fileName: FixCalculate.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  20:43:23
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class FixCalculate implements Calculatable
{
    private static Properties properties = new Properties();
    static {
        try
        {
            properties.load(FixCalculate.class.getClassLoader().getResourceAsStream("tax.properties"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public double calculator(double salary, double bonus)
    {
        return salary*Double.valueOf(properties.getProperty("salary-rate")) + bonus*Double.valueOf(properties.getProperty("bonus-rate"));
    }
}
