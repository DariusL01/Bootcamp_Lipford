package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static ArrayList<Transactions> transactions = new ArrayList<>();

    public static void main(String[] args) {
        transactions = getTransactionsFromFile();
        System.out.println("Loaded Transactions: " + transactions.size());
        Scanner scanner = new Scanner(System.in);


        while (true) {

            System.out.println(" _______________________\n" +
                    "|Potara Express         |h_ __\n" +
                    "|                       ||=|##L_\n" +
                    "|________________.====._||_|__._]\n" +
                    " `(_)(_)`       `(_)(_)\"\"\"=\"=(_)\n=== Potara Shipping Main Menu ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = scanner.next();
            scanner.nextLine();

            switch(choice){
                case "D":
                    System.out.println("Add Deposit");
                    LocalDate date = FileManager.userDate("Enter Date yyyy-mm-dd");
                    LocalTime time = FileManager.userTime("Enter Time hh:mm:ss");
                    String description = FileManager.userString("Enter Description");
                    String vendor = FileManager.userString("Enter Vendor");
                    Double amount = FileManager.userAmount("Enter Amount");

                    Transactions deposit = new Transactions(date, time, description, vendor, amount);
                    transactions.add(deposit);

                    saveTransactionsToFile(deposit);

                    System.out.println(date + "|" + time + "|" + description + "|" + vendor + "|$" + amount);

                    System.out.println("Deposit Complete!");
                    break;

                case "P":
                    System.out.println("Make Payment (Debit)");
                    LocalDate paymentDate = FileManager.userDate("Enter Date yyyy-mm-dd");
                    LocalTime paymentTime = FileManager.userTime("Enter Time hh:mm:ss");
                    String paymentDescription = FileManager.userString("Enter Description");
                    String paymentVendor = FileManager.userString("Enter Vendor");
                    Double paymentAmount = FileManager.userAmount("Enter Amount");

                    paymentAmount = -Math.abs(paymentAmount);

                    Transactions payment = new Transactions(paymentDate, paymentTime, paymentDescription, paymentVendor, paymentAmount);
                    transactions.add(payment);
                    saveTransactionsToFile(payment);

                    System.out.println(paymentDate + "|" + paymentTime + "|" + paymentDescription + "|" + paymentVendor + "|$" + paymentAmount);
                    System.out.println("Payment Complete!");
                    break;

                case "L":
                    launchLedger();
                    break;

                case "X":
                    System.out.println("Exit Program");
                    return;

                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    public static void launchLedger(){

        while (true){
            System.out.println("Ledger Menu");
            System.out.println("A) All Entries");
            System.out.println("D) Only Deposits");
            System.out.println("P) Only Payments");
            System.out.println("R) Reports Menu");
            System.out.println("H) Home Menu");

            String choice = FileManager.userString("Choose From Menu");

            switch (choice.toUpperCase()) {
                case "A":

                    System.out.println("All Entries");
                    for (int i = transactions.size() -1; i >= 0; i--) {
                        Transactions t = transactions.get(i);
                        printTransactions(t);
                    }
                    break;

                case "D":

                    System.out.println("Deposits");
                    for (int i = transactions.size() - 1; i >=0; i--) {
                        Transactions t = transactions.get(i);
                        if (t.getAmount() > 0) {
                            printTransactions(t);
                        }
                    }
                    break;

                case "P":

                    System.out.println("Payments");

                    for (int i = transactions.size() - 1; i >=0; i--) {
                        Transactions t = transactions.get(i);
                        if (t.getAmount() < 0) {
                            printTransactions(t);
                        }
                    }
                    break;

                case "R":
                    //launches reports submenu
                    launchReportsMenu();
                    break;

                case "H":
                    System.out.println("Return To Main Menu");
                    return;

                default:
                    System.out.println("Invalid Option");

            }
        }
    }

    public static void launchReportsMenu() {

        while (true) {
            System.out.println("Reports Menu");

            System.out.println("1. Month To Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year To Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search By Vendor");
            System.out.println("6. Custom Search");
            System.out.println("0. Return To Ledger Menu");

            String choice = FileManager.userString("Choose From Menu");

            switch (choice) {
                case "1": {
                    System.out.println("Month To Date");

                    LocalDate today = LocalDate.now();

                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transactions t = transactions.get(i);

                        if (t.getDate().getMonth() == today.getMonth() && t.getDate().getYear() == today.getYear()) {
                            printTransactions(t);
                        }
                    }
                }
                break;

                case "2": {
                    System.out.println("Previous Month");
                    LocalDate today = LocalDate.now();
                    LocalDate previousMonth = today.minusMonths(1);

                    for (int i = transactions.size() - 1; i >=0; i--) {
                        Transactions t = transactions.get(i);
                        LocalDate date = t.getDate();

                        if (date.getYear() == previousMonth.getYear() && date.getMonth() == previousMonth.getMonth()) {
                            printTransactions(t);
                        }
                    }
                    break;
                }

                case "3": {
                    System.out.println("Year To Date");
                    LocalDate today = LocalDate.now();
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transactions t = transactions.get(i);

                        if (t.getDate().getYear() == today.getYear()) {
                            printTransactions(t);
                        }
                    }
                    break;
                }

                case "4": {
                    System.out.println("Previous Year");

                    int previousYear = LocalDate.now().getYear() - 1;
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transactions t = transactions.get(i);

                        if (t.getDate().getYear() == previousYear) {
                            printTransactions(t);
                        }
                    }

                }
                break;

                case "5":
                    String vendorName = FileManager.userString("Vendor Name: ");
                    System.out.println("Results for | " + vendorName);
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transactions t = transactions.get(i);

                        if (t.getVendor().equalsIgnoreCase(vendorName)) {
                            printTransactions(t);
                        }
                    }
                    break;

                case "6":

                    customSearch();
                    break;

                case "0":
                    System.out.println("Return To Main Menu");
                    return;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    public static void sort(){
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            printTransactions(t);
        }
    }

    public static void sortByDeposit() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
        }
    }

    public static void sortByPayment(){
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
        }
    }

    public static void printTransactions(Transactions t) {
        System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
    }

    public static ArrayList<Transactions> getTransactionsFromFile() {

        ArrayList<Transactions> transactions = new ArrayList<>();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {

            String lineFromString;

            bufferedReader.readLine();

            while ((lineFromString = bufferedReader.readLine()) != null) {
                try {

                    if (lineFromString.trim().isEmpty()) continue;

                    String[] items = lineFromString.split("\\|");

                    LocalDate date = LocalDate.parse(items[0].trim());
                    LocalTime time = LocalTime.parse(items[1].trim());
                    String description = items[2].trim();
                    String vendor = items[3].trim();
                    double amount = Double.parseDouble(items[4].trim());

                    Transactions t = new Transactions(date, time, description, vendor, amount);
                    transactions.add(t);

                } catch (Exception ex) {
                    System.out.println("Error parsing line: " + lineFromString);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("transaction.csv not found");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }

        return transactions;
    }

    public static void writeTransactions(Transactions transactions){
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv, ", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = String.format("%s|%s|%s|%s|%.2f", transactions.getDate(), transactions.getTime(), transactions.getDescription(), transactions.getVendor(), transactions.getAmount());

            bufferedWriter.newLine();
            bufferedWriter.write(line);
            bufferedWriter.close();

            System.out.println("Write complete!");
        } catch (IOException ex) {
            System.out.println("Could not write to the file.");
        } catch (Exception ex) {
            System.out.println("Error when handling the data.");
        }
    }

    public static void saveTransactionsToFile(Transactions t) {

        try (FileWriter fileWriter = new FileWriter("transactions.csv", true)) {

            String line = String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

            fileWriter.write(line);

        } catch (Exception ex) {
            System.out.println("Error, cannot save.");
        }
    }

    public static void customSearch() {
        System.out.println("Custom Search");

        String startDateInput = FileManager.userString("Start Date (yyyy-mm-dd): ");
        String endDateInput = FileManager.userString("End Date (yyyy-mm-dd): ");
        String descriptionInput = FileManager.userString("Description: ");
        String vendorInput = FileManager.userString("Vendor: ");
        String amountInput = FileManager.userString("Amount: ");

        LocalDate startDate = null;
        LocalDate endDate = null;
        Double amount = null;

        try {
            if (!startDateInput.isEmpty()) startDate = LocalDate.parse(startDateInput);
            if (!endDateInput.isEmpty()) endDate = LocalDate.parse(endDateInput);
            if (!amountInput.isEmpty()) amount = Double.parseDouble(amountInput);
        } catch (Exception ex) {
            System.out.println("Invalid input format.");
            return;
        }

        System.out.println("Search Results");

        ArrayList<Transactions> match = new ArrayList<Transactions>();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);

            if (startDate != null && t.getDate().isBefore(startDate)) continue;
            if (endDate != null && t.getDate().isAfter(endDate)) continue;
            if (amount != null && t.getAmount() != amount) continue;
            if (!descriptionInput.isEmpty() &&
                    !t.getDescription().toLowerCase().contains(descriptionInput.toLowerCase()))
                continue;

            if (!vendorInput.isEmpty() &&
                    !t.getVendor().toLowerCase().contains(vendorInput.toLowerCase()))
                continue;

            match.add(t);
        }

        if (match.isEmpty()) {
            System.out.println("No Transactions Available.");
        } else {
            for (Transactions t : match) {
                System.out.println(t);
            }
        }
    }
}