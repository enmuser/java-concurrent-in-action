package org.example.chapter2;

/**
 * projectName: javaconcurrent
 * fileName: TaxCalculatorMain.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  20:26:52
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TaxCalculatorMain
{
    public static void main(String[] args)
    {
//        TaxCalculator taxCal = new TaxCalculator(10000d, 3000d, new Calculatable()
//        {
//            @Override
//            public double calculator(double salary, double bonus)
//            {
//                return salary*0.1 + bonus*0.15;
//            }
//        });

        TaxCalculator taxCal2 = new TaxCalculator(10000d,30000d,(x,y)->x * 0.1 + y * 0.15);
//        FixCalculate fixCalculate = new FixCalculate();
//        taxCal2.setCalculator(fixCalculate);
        System.out.println(taxCal2.calculateResult());
    }
}
