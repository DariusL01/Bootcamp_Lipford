package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public static void saveContract(Contract contract) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/contracts.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Vehicle v = contract.getVehicle();

            String common = String.format("%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());

            String line = "";

            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;
                String financeText = "NO";
                if (sale.isFinance()) {
                    financeText = "YES";
                }

                line = String.format("SALE|%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        common,
                        sale.getSalesTaxAmount(),
                        sale.getRecordingFee(),
                        sale.getProcessingFee(),
                        sale.getTotalPrice(),
                        financeText,
                        sale.getMonthlyPayment());
            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

                line = String.format("LEASE|%s|%.2f|%.2f|%.2f|%.2f",
                        common,
                        lease.getExpectedEndingValue(),
                        lease.getLeaseFee(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment());
            }
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.close();

            System.out.println("Contract saved!");
        } catch (IOException ex) {
            System.out.println("Could not write the contract to the file.");
        }
    }
}
