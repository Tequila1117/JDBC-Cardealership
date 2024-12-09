package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection connection;

    // Constructor - Initializes the connection
    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert a new entry into the 'inventory' table
    public void insertInventory(int dealershipId, String vin) throws SQLException {
        String sql = "INSERT INTO inventory (dealership_id, vin) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dealershipId);
            statement.setString(2, vin);
            statement.executeUpdate(); // Execute insert
        }
    }

    // Delete an inventory entry by dealership ID and VIN
    public void deleteInventory(int dealershipId, String vin) throws SQLException {
        String sql = "DELETE FROM inventory WHERE dealership_id = ? AND vin = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dealershipId);
            statement.setString(2, vin);
            statement.executeUpdate(); // Execute delete
        }
    }

    // Retrieve all vehicles available in a specific dealership's inventory
    public List<String> getInventoryForDealership(int dealershipId) throws SQLException {
        List<String> inventory = new ArrayList<>();
        String sql = "SELECT vin FROM inventory WHERE dealership_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dealershipId);
            ResultSet resultSet = statement.executeQuery(); // Execute query

            // Loop through result set to get all vehicle VINs for the dealership
            while (resultSet.next()) {
                inventory.add(resultSet.getString("vin"));
            }
        }
        return inventory;
    }

    // Check if a vehicle is in the inventory of a particular dealership
    public boolean isVehicleInInventory(int dealershipId, String vin) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inventory WHERE dealership_id = ? AND vin = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dealershipId);
            statement.setString(2, vin);
            ResultSet resultSet = statement.executeQuery(); // Execute query

            // Return true if there is at least one matching entry in the inventory
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }
}