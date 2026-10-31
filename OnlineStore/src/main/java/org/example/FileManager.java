package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static List<Product> readProducts(){
        List<Product> productList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try{
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;

            bufferedReader.readLine();
            while((row = bufferedReader.readLine()) != null) {
                String[] brokenUpRow = row.split("\\|");
                String sKU = brokenUpRow[0];
                String item = brokenUpRow[1];
                double price = Double.parseDouble(brokenUpRow[2]);
                String department = brokenUpRow[3];

                Product product = new Product(sKU, item, price, department);

                System.out.println(row);

                productList.add(product);
            }
        } catch (IOException ex) {
            System.out.println("Could not read file. Check the file and try again.");
        } catch (Exception ex) {
            System.out.println("Could not read data from the file. Check the format.");
        }
        return productList;
    }

    public static void writeProduct(Product product){
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/products.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = String.format("%s|%s|%.2f|%s", product.getsKU(), product.getItem(), product.getPrice(), product.getDepartment());

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
