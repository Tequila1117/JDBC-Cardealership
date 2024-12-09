package com.pluralsight.dealership;

import java.util.Scanner;
import java.util.List;


public class UserInterface {
    // The Dealership object that manages vehicle data
    private Dealership dealership;

    // Constructor to initialize the user interface
    public UserInterface() {

    }

    // Method to initialize the dealership data from a file
    private void init() {
        try {
            // Attempt to load the dealership data using the DealershipFileManager
            this.dealership = new DealershipFileManager().getDealership();
        } catch (Exception e) {
            // If there’s an error loading the data, error message will print
            System.out.println("Error loading dealership data: " + e.getMessage());
        }
    }

    // Method to display the main menu and handle user input
    public void display() {
        // Scanner object for user input
        Scanner scan = new Scanner(System.in);
        // Initialize the dealership data when the program starts
        init();
        // Variable to store the user's menu choice
        String userChoice = "";

        System.out.printf("Welcome to the Car Dealership!!!%n");

        // Loop until the user chooses to quit (option "99")
        while (!userChoice.equals("99")) {
            // Print the main menu options
            System.out.printf("""
                    ╔══════════════════════════════════════════╗
                    ║ Please select an option!                 ║
                    ╠══════════════════════════════════════════╣
                    ║ Price                              (1)   ║
                    ║ Make/Model                         (2)   ║
                    ║ Year                               (3)   ║
                    ║ Color                              (4)   ║
                    ║ Mileage                            (5)   ║
                    ║ Type                               (6)   ║
                    ║ All                                (7)   ║
                    ║ Add a vehicle                      (8)   ║
                    ║ Remove a vehicle                   (9)   ║
                    ║ Quit                               (99)  ║
                    ╚══════════════════════════════════════════╝%n""");
            // Read the user's choice
            userChoice = scan.nextLine();
            // Determine the action based on the user's choice
            switch (userChoice) {
                case "1":
                    processGetByPriceRequest(scan);
                    break;
                case "2":
                    processGetByMakeModelRequest(scan);
                    break;
                case "3":
                    processGetByYearRequest(scan);
                    break;
                case "4":
                    processGetByColorRequest(scan);
                    break;
                case "5":
                    processGetByMileageRequest(scan);
                    break;
                case "6":
                    processGetByVehicleTypeRequest(scan);
                    break;
                case "7":
                    processGetAllVehiclesRequest(); // Display all vehicles
                    break;
                case "8":
                    processAddVehicleRequest(scan);
                    break;
                case "9":
                    processRemoveVehicleRequest(scan);
                    break;
                case "99":

                    System.out.println("Thank You for Visiting the Car Dealership!");
                    break;
                default:
                    System.out.println("Please enter a valid option:");
            }
        }
        scan.close(); // Close the scanner
    }

    // Method to process a request to find vehicles by price range
    private void processGetByPriceRequest(Scanner scan) {
        System.out.println("Enter minimum price:");
        double minPrice = Double.parseDouble(scan.nextLine()); // Read minimum price
        System.out.println("Enter maximum price:");
        double maxPrice = Double.parseDouble(scan.nextLine()); // Read maximum price

        // Get a list of vehicles within the specified price range
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        // Display the vehicles found
        displayVehicles(vehicles);
    }

    // Method to process a request to find vehicles by make and model
    private void processGetByMakeModelRequest(Scanner scan) {
        System.out.println("Enter make:");
        String make = scan.nextLine(); // Read the vehicle make
        System.out.println("Enter model:");
        String model = scan.nextLine(); // Read the vehicle model

        // Get a list of vehicles matching the make and model
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        // Display the vehicles found
        displayVehicles(vehicles);
    }

    // Method to process a request to find vehicles by year range
    private void processGetByYearRequest(Scanner scan) {
        System.out.println("Enter minimum year:");
        int minYear = Integer.parseInt(scan.nextLine()); // Read minimum year
        System.out.println("Enter maximum year:");
        int maxYear = Integer.parseInt(scan.nextLine()); // Read maximum year

        // Get a list of vehicles within the specified year range
        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        // Display the vehicles found
        displayVehicles(vehicles);
    }

    // Method to process a request to find vehicles by color
    private void processGetByColorRequest(Scanner scan) {
        System.out.println("Enter color:");
        String color = scan.nextLine(); // Read the vehicle color

        // Get a list of vehicles matching the specified color
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        // Display the vehicles found
        displayVehicles(vehicles);
    }

    // Method to process a request to find vehicles by mileage range
    private void processGetByMileageRequest(Scanner scan) {
        System.out.println("Enter minimum mileage:");
        int minMileage = Integer.parseInt(scan.nextLine()); // Read minimum mileage
        System.out.println("Enter maximum mileage:");
        int maxMileage = Integer.parseInt(scan.nextLine()); // Read maximum mileage

        // Get a list of vehicles within the specified mileage range
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        // Display the vehicles found
        displayVehicles(vehicles);
    }

    // Method to process a request to find vehicles by type
    private void processGetByVehicleTypeRequest(Scanner scan) {
        System.out.println("Enter vehicle type:");
        String vehicleType = scan.nextLine(); // Read the vehicle type

        // Get a list of vehicles matching the specified type
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        // Display the vehicles found
        displayVehicles(vehicles);
    }

    // Method to process a request to display all vehicles
    private void processGetAllVehiclesRequest() {
        // Get a list of all vehicles in the dealership
        List<Vehicle> vehicles = dealership.getAllVehicles();
        // Display the vehicles found
        displayVehicles(vehicles);
    }
    // Method to process a request to add a new vehicle
    private void processAddVehicleRequest(Scanner scan) {
        System.out.println("Enter vehicle details (VIN, Year, Make, Model, Type, Color, Odometer, Price):");
        String[] details = scan.nextLine().split(","); // Read vehicle details and split by commas
        // Check if the correct number of details was provided
        if (details.length == 8) {
            // Create a new Vehicle object using the provided details
            int vin = Integer.parseInt(details[0].trim());
            int year = Integer.parseInt(details[1].trim());
            String make = details[2].trim();
            String model = details[3].trim();
            String type = details[4].trim();
            String color = details[5].trim();
            int odometer = Integer.parseInt(details[6].trim());
            double price = Double.parseDouble(details[7].trim());

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            // Add the new vehicle to the dealership
            dealership.addVehicle(vehicle);
            System.out.println("Vehicle added successfully!");

        } else {
            System.out.println("Invalid input. Please provide all vehicle details.");
        }
    }

    // Method to process a request to remove a vehicle by VIN
    private void processRemoveVehicleRequest(Scanner scan) {
        System.out.println("Enter VIN of the vehicle to remove:");
        int vin = Integer.parseInt(scan.nextLine()); // Read the VIN to remove
        // Create a placeholder Vehicle object for searching
        Vehicle vehicleToRemove = new Vehicle(vin, 0, "", "", "", "", 0, 0.0);
        // Remove the vehicle from the dealership
        dealership.removeVehicle(vehicleToRemove);
        System.out.println("Vehicle removed successfully!"); // Confirmation message

        // Call saveDealership to persist changes
        saveDealership(dealership);
    }
    private void saveDealership(Dealership dealership) {
        try {
            // Assuming you have a DealershipFileManager class that saves the data
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);
            System.out.println("Dealership data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving dealership info: " + e.getMessage());
        }
    }

    // Method to display the list of vehicles found
    private void displayVehicles(List<Vehicle> vehicles) {
        // Check if the list is empty
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found."); // Inform the user if no vehicles match the criteria
        } else {
            // Iterate through the list and print each vehicle's details
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
}