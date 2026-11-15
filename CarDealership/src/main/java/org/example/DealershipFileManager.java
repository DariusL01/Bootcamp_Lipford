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

    public static void writeVehicle(Vehicle vehicle) {
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/slinventory.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = String.format("%f|%f|%s|%s|%s|%s|%f|%.2f", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());

            bufferedWriter.newLine();
            bufferedWriter.write(line);

            System.out.println("Write complete!");
        } catch (IOException ex) {
            System.out.println("Could not write to the file.");
        } catch (Exception ex) {
            System.out.println("Error when handling the data.");
        }
    }
}
