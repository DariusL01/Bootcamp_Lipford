package org.example;

import java.lang.classfile.attribute.SourceFileAttribute;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

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
            System.out.println("10) Sell/Lease a vehicle");
            System.out.println("99) Exit");

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

                case 10:
                    processSellOrLeaseRequest();
                    break;

                case 99:
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

        DealershipFileManager.saveDealership(dealership);

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
            DealershipFileManager.saveDealership(dealership);

            System.out.println("Vehicle removed from inventory.");

        } else {

            System.out.println("Vehicle not found.");
        }
    }

    public void processSellOrLeaseRequest() {

        System.out.println("Enter VIN of the vehicle: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getInventoryList()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("Customer Name: ");
        String name = scanner.nextLine();

        System.out.println("Customer Email: ");
        String email = scanner.nextLine();

        //Date as a string formatted like 20261122
        String date = LocalDate.now().toString().replace("-", "");

        System.out.println("Is this a (S)ale or (L)ease? ");
        String choice = scanner.nextLine();

        Contract contract = null;

        if (choice.equalsIgnoreCase("S")) {
            System.out.println("Do you want to finance? (yes/no): ");
            String financeAnswer = scanner.nextLine();
            boolean finance = financeAnswer.equalsIgnoreCase("yes");

            contract = new SalesContract(date, name, email, vehicle, finance);

        } else if (choice.equalsIgnoreCase("L")) {
            //Can't lease a vehicle over 3 years old
            if (LocalDate.now().getYear() - vehicle.getYear() > 3) {
                System.out.println("Sorry, vehicles over 3 years old can't be leased.");
                return;
            }

            contract = new LeaseContract(date, name, email, vehicle);

        } else {
            System.out.println("Invalid choice.");
            return;
        }
        System.out.printf("Total Price: $%.2f%n", contract.getTotalPrice());
        System.out.printf("Monthly Payment: $%.2f%n", contract.getMonthlyPayment());

        //Update inventory file
        ContractDataManager.saveContract(contract);
        dealership.getInventoryList().remove(vehicle);
        DealershipFileManager.saveDealership(dealership);
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