package Calculators;

import java.util.Scanner;

public class Calculator1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //r is interest rate 0.07625
        //i is monthly interest rate
        //y is years 15
        //n is number of monthly payments 12
        //tI is total interest
        //p is the principal or loan amount
        //m is monthly payments

        double r = 0.07625;
        double n = 12;
        double p = 53000;
        double i = (r / 12);
        double m = p * (i * Math.pow((1 + i),12)) / (Math.pow((1 + i),12) - 1);
        double tI = (m * n) - p;

        System.out.println("Loan length: ");
        double y = scanner.nextDouble();



    }
}
