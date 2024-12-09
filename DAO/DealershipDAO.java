package DAO;

import com.pluralsight.dealership.Dealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password"; // Change to your MySQL password

    // Create a new dealership
    public void createDealership(Dealership dealership) {
        String query = "INSERT INTO dealership (name, address, phone) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getAddress());
            stmt.setString(3, dealership.getPhone());

            stmt.executeUpdate();
            System.out.println("Dealership created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all dealerships
    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealership";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Dealership dealership = new Dealership(
                        rs.getInt("dealership_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dealerships;
    }

    // Retrieve a dealership by ID
    public Dealership getDealershipById(int dealershipId) {
        Dealership dealership = null;
        String query = "SELECT * FROM dealership WHERE dealership_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, dealershipId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dealership = new Dealership(
                        rs.getInt("dealership_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dealership;
    }

    // Update a dealership
    public void updateDealership(Dealership dealership) {
        String query = "UPDATE dealership SET name = ?, address = ?, phone = ? WHERE dealership_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getAddress());
            stmt.setString(3, dealership.getPhone());
            stmt.setInt(4, dealership.getId());

            stmt.executeUpdate();
            System.out.println("Dealership updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a dealership
    public void deleteDealership(int dealershipId) {
        String query = "DELETE FROM dealership WHERE dealership_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, dealershipId);
            stmt.executeUpdate();
            System.out.println("Dealership deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}