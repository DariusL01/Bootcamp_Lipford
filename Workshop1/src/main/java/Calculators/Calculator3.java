package Calculators;

import java.util.Scanner;

public class Calculator3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Monthly payout: ");
        double mPayout = scanner.nextDouble();

        System.out.printf("Interest rate: ");
        double iR = scanner.nextDouble() / 100;

        System.out.printf("Years of payout: ");
        double y = scanner.nextDouble();

        double monthlyRate = iR / 12;

        //n is number of payments
        double n = y * 12;

        double intP = Math.pow(1 + monthlyRate, n);
        double totalP = 1 -(1/ intP);
        double pv = mPayout * (totalP / monthlyRate);

        System.out.printf("Annuity: %.2f%n", pv);

        scanner.close();

    }
}