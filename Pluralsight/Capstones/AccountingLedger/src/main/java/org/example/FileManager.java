package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    //scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    public static String userString(String user){
        System.out.println(user + ": ");
        return scanner.nextLine();
    }

    public static LocalDate userDate(String user){
        while (true){
            try{
                System.out.println(user + ": ");
                String input = scanner.nextLine();

                return LocalDate.parse(input);

            } catch (Exception ex) {
                System.out.println("Invalid Date Format. Use yyyy-mm-dd.");
            }
        }
    }
    public static LocalTime userTime(String user){
        while(true){
            try{
                System.out.println(user + ": ");
                String input = scanner.nextLine();

                return LocalTime.parse(input);

            }catch (Exception ex){
                System.out.println("Invalid Time Format. Use hh:mm:ss.");
            }
        }
    }
    public static double userAmount(String user){
        while (true){
            try {
                System.out.println(user + ": ");
                double result = scanner.nextDouble();
                scanner.nextLine();
                return result;
            } catch (Exception ex) {
                System.out.println("Invalid amount. Try again.");
            }
        }

    }

}