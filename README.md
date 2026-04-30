# Smart City Management System (JDBC-Based)

##  Overview

The **Smart City Management System** is a modular Java-based desktop application designed to simulate and manage key urban services such as citizen records, complaints, utilities, and ticketing. The system uses **JDBC (Java Database Connectivity)** to interact with a relational database and follows a layered architecture to ensure scalability, maintainability, and separation of concerns.


##  System Architecture

The application is structured using a **multi-layered architecture**:

### 1. Presentation Layer (UI)

* Implemented using Java Swing
* Handles user interaction and input validation
* Components:

  * Main Dashboard (`MainFrame`)
  * Feature Panels (CitizenPanel, ComplaintPanel, etc.)

### 2. Business Logic Layer

* Acts as an intermediary between UI and database
* Contains core logic for processing operations
* Ensures data consistency and validation

### 3. Data Access Layer (DAO Pattern)

* Encapsulates all database operations
* Uses **DAO (Data Access Object)** design pattern
* Provides CRUD operations for each entity:

  * `CitizenDAO`
  * `ComplaintDAO`
  * `ElectricityDAO`
  * `WaterDAO`
  * `PaymentDAO`
  * `TicketDAO`

### 4. Database Layer

* Managed using MySQL
* Schema defined in `database.sql`
* Uses relational tables with proper normalization


## Technologies Used

* **Programming Language:** Java (JDK 8+)
* **Database:** MySQL
* **Connectivity:** JDBC API
* **UI Framework:** Java Swing
* **Version Control:** Git
* **Repository Hosting:** GitHub

## Core Functional Modules

### Citizen Management

* Add, update, delete citizen records
* Unique identification using IDs
* Data validation and persistence

### Complaint Management

* Register and track complaints
* Categorization and status updates
* Linked to specific citizens

### Electricity Management

* Track electricity usage
* Calculate billing based on consumption
* Store historical usage data

### Water Management

* Monitor water consumption
* Billing and reporting

### Payment System

* Record transactions
* Maintain payment history
* Link payments to services

### Ticketing System

* Manage transportation tickets
* Store booking and travel details



## Database Design

* Fully relational schema
* Tables include:

  * `citizens`
  * `complaints`
  * `electricity_usage`
  * `water_usage`
  * `payments`
  * `tickets`
* Uses:

  * Primary Keys
  * Foreign Key relationships
  * Data normalization (up to 3NF)


## JDBC Integration

* Database connection handled via `DBConnection.java`

* Uses standard JDBC workflow:

  1. Load driver
  2. Establish connection
  3. Create prepared statements
  4. Execute queries
  5. Process results

* Example:

```java
Connection con = DriverManager.getConnection(url, user, password);
PreparedStatement ps = con.prepareStatement("SELECT * FROM citizens");
ResultSet rs = ps.executeQuery();
```

---

## Setup & Execution

### 1. Clone Repository

```bash
git clone https://github.com/km9241/citymanagment.git
cd citymanagment
```

### 2. Database Setup

* Open MySQL
* Import `database.sql`:

```bash
mysql -u root -p < database.sql
```

### 3. Configure Database Connection

Update credentials in:

```
DBConnection.java
```

### 4. Compile and Run

```bash
javac -d bin src/**/*.java
java -cp bin main.Main
```
 Key Features

* Modular and extensible architecture
* Separation of concerns using DAO pattern
* Efficient database interaction using prepared statements
* GUI-based interaction for usability
* Scalable design for adding new services

 Security Considerations

* Uses Prepared Statements to prevent SQL Injection
* Avoids hardcoding sensitive credentials (recommended improvement: environment variables)

 Future Enhancements

* Web-based interface (Spring Boot / React)
* REST API integration
* Authentication and role-based access control
* Cloud database deployment
* Real-time analytics dashboard



## Author

**Keshav Maheswari**



## License

This project is for academic and educational purposes.
