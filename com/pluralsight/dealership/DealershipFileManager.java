package com.pluralsight.dealership;


import java.io.*;


public class DealershipFileManager {
    private final String filePath = "src/main/resources/inventory.csv"; // File path for dealership data

    public Dealership getDealership() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        String[] dealershipInfo = line.split("\\|");
        Dealership dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

        while ((line = reader.readLine()) != null) {
            String[] vehicleData = line.split("\\|");
            Vehicle vehicle = new Vehicle(
                    Integer.parseInt(vehicleData[0]),
                    Integer.parseInt(vehicleData[1]),
                    vehicleData[2],
                    vehicleData[3],
                    vehicleData[4],
                    vehicleData[5],
                    Integer.parseInt(vehicleData[6]),
                    Double.parseDouble(vehicleData[7])
            );
            dealership.addVehicle(vehicle); // Add vehicle to dealership inventory
        }
        reader.close();
        return dealership; // Return the populated dealership object
    }

    public void saveDealership(Dealership dealership) {
        //  file path where the dealership data will be saved
        String filePath = "src/main/resources/inventory.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write dealership information
            writer.write("Dealership Name: " + dealership.getName() + "\n");
            writer.write("Dealership Address: " + dealership.getAddress() + "\n");
            writer.write("Dealership Phone: " + dealership.getPhone() + "\n");

            // Write vehicle information
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice()));
            }

            System.out.println("Dealership data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving dealership data: " + e.getMessage());

        }
    }
}