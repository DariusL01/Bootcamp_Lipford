package Calculators;

import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.printf("Principal: ");
        double p = scanner.nextDouble();

        System.out.printf("Annual Interest Rate: ");
        double r = scanner.nextDouble() / 100;

        System.out.printf("Number of years: ");
        double t = scanner.nextDouble();

        //n is days per year

        double d = 365;
        double n = d * t;
        double fv = p * Math.pow(1 + (r / d), n);

        System.out.printf("Total interest earned: %.2f%n", fv - p);

        scanner.close();


    }
}
