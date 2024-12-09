package DAO;

import com.pluralsight.dealership.SalesContract;
import com.pluralsight.dealership.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password"; // Change to your MySQL password

    // Create a new sales contract
    public void createSalesContract(SalesContract contract) {
        String query = "INSERT INTO sales_contracts (vin, sale_date, sale_price, customer_name) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, contract.getVin());
            stmt.setDate(2, contract.getSaleDate());  // Assuming it's a java.sql.Date
            stmt.setDouble(3, contract.getSalePrice());
            stmt.setString(4, contract.getCustomerName());

            stmt.executeUpdate();
            System.out.println("Sales contract created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all sales contracts
    public List<SalesContract> getAllSalesContracts() {
        List<SalesContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                SalesContract contract = new SalesContract(
                        rs.getString("sale_date"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        new Vehicle(rs.getString("vin"), rs.getString("vehicle_model")),
                        rs.getString("financing_option")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }
}