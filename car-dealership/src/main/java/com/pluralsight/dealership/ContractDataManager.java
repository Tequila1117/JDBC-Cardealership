package com.pluralsight.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private final String contractsFilePath = "src/main/resources/contracts.csv"; // File path for stored contracts

    // Saves a contract to the contracts file
    //  The contract to save (SalesContract or LeaseContract)
    // IOException If there's an issue with file writing
    public void saveContract(Contract contract) throws IOException {
        // BufferedWriter to append to the contracts file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(contractsFilePath, true))) {
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract; // Cast to SalesContract
                // Format data for sales contract
                String data = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        salesContract.getDate(),
                        salesContract.getCustomerName(),
                        salesContract.getCustomerEmail(),
                        salesContract.getVehicleSold().getVin(),
                        salesContract.getVehicleSold().getYear(),
                        salesContract.getVehicleSold().getMake(),
                        salesContract.getVehicleSold().getModel(),
                        salesContract.getVehicleSold().getVehicleType(),
                        salesContract.getVehicleSold().getColor(),
                        salesContract.getTotalPrice(),
                        salesContract.getMonthlyPayment(),
                        salesContract.getSalesTaxAmount(),
                        salesContract.getRecordingFee(),
                        salesContract.getProcessingFee(),
                        salesContract.isFinanced() ? "YES" : "NO");


                // Write the formatted sales contract data to the file
                writer.write(data);
                writer.newLine(); // Move to the next line
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract; // Cast to LeaseContract


                // Format data for lease contract
                String data = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%.2f|%.2f",
                        leaseContract.getDate(),
                        leaseContract.getCustomerName(),
                        leaseContract.getCustomerEmail(),
                        leaseContract.getVehicleSold().getVin(),
                        leaseContract.getVehicleSold().getYear(),
                        leaseContract.getVehicleSold().getMake(),
                        leaseContract.getVehicleSold().getModel(),
                        leaseContract.getVehicleSold().getVehicleType(),
                        leaseContract.getVehicleSold().getColor(),
                        leaseContract.getTotalPrice(),
                        leaseContract.getMonthlyPayment());

                // Write the formatted lease contract data to the file
                writer.write(data);
                writer.newLine(); // Move to the next line
            }
        }
    }
}