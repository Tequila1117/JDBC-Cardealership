package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password"; // Change to your MySQL password

    // Create a new lease contract
    public void createLeaseContract(LeaseContract contract) {
        String query = "INSERT INTO lease_contracts (vin, lease_start, lease_end, lease_price, customer_name) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, contract.getVin());
            stmt.setDate(2, contract.getLeaseStart());
            stmt.setDate(3, contract.getLeaseEnd());
            stmt.setDouble(4, contract.getLeasePrice());
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
                        rs.getInt("id"),
                        rs.getString("vin"),
                        rs.getDate("lease_start"),
                        rs.getDate("lease_end"),
                        rs.getDouble("lease_price"),
                        rs.getString("customer_name")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }
}