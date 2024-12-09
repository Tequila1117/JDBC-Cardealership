package com.pluralsight.dealership;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private final String name;
    private final String address;
    private final String phone;
    private final ArrayList<Vehicle> inventory;


    // Initialize inventory list
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Add vehicle to the inventory
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }


    // Remove vehicle from the inventory
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    // Return a copy of the inventory
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                result.add(vehicle);
            }
        }
        return result; // Return the list of vehicles within the price range
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                result.add(vehicle);
            }
        }
        return result; // Return the list of vehicles matching the make and model
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                result.add(vehicle);
            }
        }
        return result; // Return the list of vehicles within the year range
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                result.add(vehicle);
            }
        }
        return result; // Return the list of vehicles of specific color
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                result.add(vehicle);
            }
        }
        return result; // Return the list of vehicles within the mileage range
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(vehicle);
            }
        }
        return result; // Return the list of vehicles of the specified type
    }

    // Getters for other fields if needed
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
