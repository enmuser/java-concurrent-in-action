package org.example.chapter2;

/**
 * projectName: javaconcurrent
 * fileName: TaxCalculator.java
 * packageName: org.example.chapter2
 * date: 2020年01月20日  20:23:33
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class TaxCalculator
{
    private final double salary;

    private final double bonus;

    private Calculatable calculator;

    public TaxCalculator(double salary, double bonus,Calculatable calculator)
    {
        this.salary = salary;
        this.bonus = bonus;
        this.calculator = calculator;
    }

    public TaxCalculator(double salary, double bonus)
    {
        this.salary = salary;
        this.bonus = bonus;
    }

    public void setCalculator(Calculatable calculator)
    {
        this.calculator = calculator;
    }

    public double calculateResult(){
        return calculator.calculator(salary,bonus);
    }
}
