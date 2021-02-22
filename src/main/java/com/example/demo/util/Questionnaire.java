package com.example.demo.util;

import java.util.Random;

public class Questionnaire {

    public static String Addition(int max) {
        int add1,add2;
        Random random = new Random();
        do {
            add1=random.nextInt(max);
            add2=random.nextInt(max);
        } while (add1==0 | add2==0 | add1==add2 );
        return (add1+"+"+add2);

    }
    public static String Subtraction(int max) {
        int minuend, subtracted;
        Random random = new Random();
        do {
            minuend = random.nextInt(max);
            subtracted = random.nextInt(max);
        } while (minuend <= subtracted | subtracted==0);
        return (minuend+"-"+subtracted);
    }
    public static String AdditionAndSubtraction(int max){
        int first,second,third;
        String expression;
        String[] symbol={"+","-"};
        Random random = new Random();
        Calculate calculate = new Calculate();
        do {
            do {
                first = random.nextInt(max);
            } while (first == 0);
            do {
                second = random.nextInt(max);
            } while (second == 0);
            do {
                third = random.nextInt(max);
            } while (third == 0);
            expression = ""+first+symbol[random.nextInt(symbol.length)]+second+symbol[random.nextInt(symbol.length)]+third;
        } while (calculate.calculate(expression)<0 | first<=second);
        return expression;
    }
    public static String Multiplication(int max) {
        int multi1,multi2;
        Random random = new Random();
        do {
            multi1=random.nextInt(max);
            multi2=random.nextInt(max);
        } while (multi1<2 | multi2<2 );
        return (multi1+"*"+multi2);
    }
    public static String Division(int max) {
        int dividend, divisor;
        Random random = new Random();
        do {
            dividend = random.nextInt(max);
            divisor = random.nextInt(9);
        } while (divisor<2 | divisor < 2 || dividend/divisor > 10 || dividend%divisor!=0 ||divisor==dividend);
        return (dividend+"/"+divisor);
    }
    public static String arithmeticMix(int max){
        int first,second,third;
        String symbol1,symbol2;
        String expression;
        String[] symbol={"+","-","*","/"};
        Random random = new Random();
        symbol1=symbol[random.nextInt(symbol.length)];
        symbol2=symbol[random.nextInt(symbol.length)];
        Calculate calculate = new Calculate();
        do {
            do {
                do {
                    first = random.nextInt(max);
                } while (first == 0);
                do {
                    second = random.nextInt(max);
                } while (second == 0);
                do {
                    third = random.nextInt(max);
                } while (third == 0);
            }
            while((symbol1.equals("/") & first%second!=0) || (symbol2.equals("/") & second%third!=0));
            expression = ""+first+symbol1+second+symbol2+third;
        } while (calculate.calculate(expression)<1);
        return expression;
    }
}