package DAO;

import com.pluralsight.dealership.LeaseContract;
import com.pluralsight.dealership.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yearup"; // Change to your MySQL password

    // Create a new lease contract
    public void createLeaseContract(LeaseContract contract) {
        String query = "INSERT INTO lease_contracts (vin, lease_start, lease_end, lease_price, customer_name) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set values for the lease contract
            stmt.setString(1, contract.getVehicleSold().getVin());
            stmt.setDate(2, Date.valueOf(contract.getDate()));  // Assuming 'date' is a String, you can convert it to Date
            stmt.setDate(3, Date.valueOf(contract.getDate()));  // Assuming 'date' is a String, you can convert it to Date
            stmt.setDouble(4, contract.getTotalPrice());
            stmt.setString(5, contract.getCustomerName());

            stmt.executeUpdate();
            System.out.println("Lease contract created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all lease contracts
    public List<LeaseContract> getAllLeaseContracts() {
        List<LeaseContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                LeaseContract contract = new LeaseContract(
                        rs.getString("date"), // Assuming this is the contract date
                        rs.getString("customer_name"),  // Customer's name
                        rs.getString("customer_email"),  // Assuming there's an email in the DB as well
                        new Vehicle(rs.getString("vin"), rs.getString("model"))  // Assuming Vehicle is a class that takes vin and model
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }
}