# 🚗 Dealership Management System - DAO Overview

This README provides an overview of the DAO (Data Access Object) layer in the **Dealership Management System**, explaining how the DAO classes manage interactions with the database.


# 📦 Table of Contents

- 🔑 Setup
- 🛠 DAO Classes
- 🏢 DealershipDAO
- 🚗 VehicleDAO
- 🗃 InventoryDAO
- 📜 SalesContractDAO
- 💼 LeaseContractDAO
- 📖 Usage
- ⚙️ Notes

# 🔑 Setup

Before using the DAOs, ensure the following:

**1. Database:** The required tables (dealership, vehicles, sales_contracts, lease_contracts, inventory) must be created in your MySQL database.

**2. JDBC Connection:** Connect to the MySQL database using JDBC. 

**3. Dependencies:** Use Maven or Gradle for the MySQL JDBC driver.

# 🛠 DAO Classes
## 🏢 DealershipDAO

Manages CRUD operations for the **dealership** table.

**Methods:** insertDealership, getAllDealerships, getDealershipById, updateDealership, deleteDealership

## 🚗 VehicleDAO

Handles operations for the **vehicles** table.

**Methods:** insertVehicle, updateVehicle, deleteVehicle, getVehicleByVin, getAllVehicles

## 🗃 InventoryDAO

Links vehicles with dealerships in the **inventory** table.

Methods: insertInventory, deleteInventory, getInventoryForDealership

## 📜 SalesContractDAO

Manages CRUD operations for **sales contracts** in the database.

Methods: insertSalesContract, getSalesByDealership, getSalesByCustomer

## 💼 LeaseContractDAO

Handles CRUD operations for **lease contracts.**

Methods: insertLeaseContract, getLeaseContractsByCustomer, getLeaseContractsByDealership

# 📖 Usage

###  1. Create DAO Instances

   To interact with tables, create an instance of the relevant DAO:


`VehicleDAO vehicleDAO = new VehicleDAO(connection);
DealershipDAO dealershipDAO = new DealershipDAO(connection);`

### 2. Perform CRUD Operations
   
Example of inserting a vehicle:


`Vehicle vehicle = new Vehicle("1A2B3C4D5E6F7G8H9I0J", 2021, 25000.00, "Toyota", "Camry", "Red", false);
vehicleDAO.insertVehicle(vehicle);`

### 3. Close Connection

   Always close the connection after use:

`connection.close();`
