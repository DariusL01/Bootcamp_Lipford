package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DealershipFileManager {
    public static List<Vehicle> readVehicles(){
        List<Vehicle> vehicleList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try{
            FileReader fileReader = new FileReader("src/main/resources/slinventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;

            bufferedReader.readLine();
            while((row = bufferedReader.readLine()) !=null) {
                String[] brokenUpRow = row.split("\\|");
                int vin = Integer.parseInt(brokenUpRow[0]);
                int year = Integer.parseInt(brokenUpRow[1]);
                String make = brokenUpRow[2];
                String model = brokenUpRow[3];
                String vehicleType = brokenUpRow[4];
                String color = brokenUpRow[5];
                int odometer = Integer.parseInt(brokenUpRow[6]);
                BigDecimal price = new BigDecimal(brokenUpRow[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                vehicleList.add(vehicle);
            }
        } catch (IOException ex) {
            System.out.println("Could not read file. Check the file and try again.");
        } catch (Exception ex) {
            System.out.println("Could not read data from the file. Check the format.");
        }
        return vehicleList;
    }

    public static void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resouces/slinventory.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bufferedWriter.newLine();

            for (Vehicle vehicle : dealership.getInventoryList()) {
                String line = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice());
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Inventory saved!");
        } catch (IOException ex) {
            System.out.println("Could not write to the file.");
        }
    }
}
