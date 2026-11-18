package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner;

    public UserInterface() {

        List<Vehicle> inventory = DealershipFileManager.readVehicles();

        dealership = new Dealership(
                "Street League Cars",
                "616 Cross Rd",
                "704-555-5555",
                inventory
        );

        scanner = new Scanner(System.in);
    }

    public void display() {

        boolean running = true;

        while (running) {

            System.out.println("\n=== STREET LEAGUE CARS ===");
            System.out.println("1) Find vehicles within price range");
            System.out.println("2) Find vehicles by make/model");
            System.out.println("3) Find vehicles by year range");
            System.out.println("4) Find vehicles by color");
            System.out.println("5) Find vehicles by mileage range");
            System.out.println("6) Find vehicles by vehicle type");
            System.out.println("7) List ALL vehicles");
            System.out.println("8) Add vehicle");
            System.out.println("9) Remove vehicle");
            System.out.println("0) Exit");

            System.out.print("Select Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    processGetByPriceRequest();
                    break;

                case 2:
                    processGetByMakeModelRequest();
                    break;

                case 3:
                    processGetByYearRequest();
                    break;

                case 4:
                    processGetByColorRequest();
                    break;

                case 5:
                    processGetByMileageRequest();
                    break;

                case 6:
                    processGetByVehicleTypeRequest();
                    break;

                case 7:
                    processGetAllVehiclesRequest();
                    break;

                case 8:
                    processAddVehicleRequest();
                    break;

                case 9:
                    processRemoveVehicleRequest();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void processGetByPriceRequest() {

        System.out.print("Minimum Price: ");
        BigDecimal minPrice = scanner.nextBigDecimal();

        System.out.print("Maximum Price: ");
        BigDecimal maxPrice = scanner.nextBigDecimal();

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getPrice().compareTo(minPrice) >= 0
                    && vehicle.getPrice().compareTo(maxPrice) <= 0) {

                displayVehicle(vehicle);
            }
        }
    }

    public void processGetByMakeModelRequest() {

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getMake().equalsIgnoreCase(make)
                    && vehicle.getModel().equalsIgnoreCase(model)) {

                displayVehicle(vehicle);
            }
        }
    }

    public void processGetByYearRequest() {

        System.out.print("Minimum Year: ");
        int minYear = scanner.nextInt();

        System.out.print("Maximum Year: ");
        int maxYear = scanner.nextInt();

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getYear() >= minYear
                    && vehicle.getYear() <= maxYear) {

                displayVehicle(vehicle);
            }
        }
    }

    public void processGetByColorRequest() {

        System.out.print("Color: ");
        String color = scanner.nextLine();

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getColor().equalsIgnoreCase(color)) {

                displayVehicle(vehicle);
            }
        }
    }

    public void processGetByMileageRequest() {

        System.out.print("Minimum Mileage: ");
        int minMileage = scanner.nextInt();

        System.out.print("Maximum Mileage: ");
        int maxMileage = scanner.nextInt();

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getOdometer() >= minMileage
                    && vehicle.getOdometer() <= maxMileage) {

                displayVehicle(vehicle);
            }
        }
    }

    public void processGetByVehicleTypeRequest() {

        scanner.nextLine();

        System.out.print("Vehicle Type: ");
        String type = scanner.nextLine();

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {

                displayVehicle(vehicle);
            }
        }
    }

    public void processGetAllVehiclesRequest() {

        for (Vehicle vehicle : dealership.getInventoryList()) {

            displayVehicle(vehicle);
        }
    }

    public void processAddVehicleRequest() {

        System.out.print("VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Vehicle Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Price: ");
        BigDecimal price = scanner.nextBigDecimal();

        Vehicle vehicle = new Vehicle(
                vin,
                year,
                make,
                model,
                type,
                color,
                odometer,
                price
        );

        dealership.getInventoryList().add(vehicle);

        DealershipFileManager.writeVehicle(vehicle);

        System.out.println("Vehicle added.");
    }

    public void processRemoveVehicleRequest() {

        System.out.print("Enter VIN to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : dealership.getInventoryList()) {

            if (vehicle.getVin() == vin) {

                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {

            dealership.getInventoryList().remove(vehicleToRemove);

            System.out.println("Vehicle removed from inventory.");

        } else {

            System.out.println("Vehicle not found.");
        }
    }

    private void displayVehicle(Vehicle vehicle) {

        System.out.printf(
                "%d | %d | %s | %s | %s | %s | %d miles | $%s%n",
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice()
        );
    }
}