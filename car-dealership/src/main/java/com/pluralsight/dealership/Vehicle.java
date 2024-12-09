package com.pluralsight.dealership;

public class Vehicle {
    private int vin;
    private int year;
    private String make; // Make of the vehicle (ex. Ford)
    private String model; // Model of the vehicle (ex. Explorer)
    private String vehicleType; // Type (car, truck, SUV, etc.)
    private String color;
    private int odometer; // Mileage of the vehicle
    private double price;


    // Constructor
    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }


    // Getters and Setters
    public int getVin() { return vin; }
    public int getYear() { return year; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getVehicleType() { return vehicleType; }
    public String getColor() { return color; }
    public int getOdometer() { return odometer; }
    public double getPrice() { return price; }


    @Override

    public String toString() {
        return vin + "|" + year + "|" + make + "|" + model + "|" + vehicleType + "|" + color + "|" + odometer + "|" + price;
    }
}