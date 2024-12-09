package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password"; // Change to your MySQL password

    // Create a new vehicle
    public void createVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, year, price, make, model, color, sold) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setDouble(3, vehicle.getPrice());
            stmt.setString(4, vehicle.getMake());
            stmt.setString(5, vehicle.getModel());
            stmt.setString(6, vehicle.getColor());
            stmt.setBoolean(7, vehicle.isSold());

            stmt.executeUpdate();
            System.out.println("Vehicle created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getBoolean("sold")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    // Retrieve a vehicle by VIN
    public Vehicle getVehicleByVin(String vin) {
        Vehicle vehicle = null;
        String query = "SELECT * FROM vehicles WHERE vin = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, vin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vehicle = new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getBoolean("sold")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    // Update a vehicle
    public void updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET year = ?, price = ?, make = ?, model = ?, color = ?, sold = ? WHERE vin = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, vehicle.getYear());
            stmt.setDouble(2, vehicle.getPrice());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getColor());
            stmt.setBoolean(6, vehicle.isSold());
            stmt.setString(7, vehicle.getVin());

            stmt.executeUpdate();
            System.out.println("Vehicle updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a vehicle by VIN
    public void deleteVehicle(String vin) {
        String query = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, vin);
            stmt.executeUpdate();
            System.out.println("Vehicle deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}