package Calculators;

import java.util.Scanner;

public class Calculator1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //r is annual interest rate 7.625
        //i is monthly interest rate
        //y is years of loan 15
        //n is total number of monthly payments 12
        //tI is total interest
        //p is the principal or loan amount
        //m is monthly payments

        //double r = 7.625 / 100;


        //display expected monthly payment & total interest
        System.out.printf("Annual Interest Rate: ");
        double r = scanner.nextDouble() / 100;

        System.out.println("Loan length in years: ");
        double y = scanner.nextDouble();


        System.out.println("Loan amount: ");
        double p = scanner.nextDouble();

        double n = y * 12;
        double i = r /12;
        double m = p * (i * Math.pow(1 + i,n)) / (Math.pow(1 + i,n) - 1);
        double tI = (m * n) - p;

        System.out.printf("Monthly payment: %.2f%n", m);
        System.out.printf("Total Interest paid: %.2f%n", tI);

        scanner.close();

    }
}
