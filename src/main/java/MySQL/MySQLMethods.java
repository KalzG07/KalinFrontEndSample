package MySQL;


import java.sql.*;
import java.util.logging.Level;

import static TestAndReporting.ExtentReport.*;
import static base.Driver.LOGGER;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/20
 */
public class MySQLMethods {
    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USER = "test_user";
    private static final String PASSWORD = "test_password";
    static String dbName = "test_db";  // Database name should be test_db or the one where your table resides
    static String tableName = "user_info";  // Table name

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void checkAndCreateDatabase() {
        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "first_name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "postal_code VARCHAR(10)" +
                ");";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement()) {

            // Check if the database exists
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();

            boolean dbExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (databaseName.equals(dbName)) {
                    dbExists = true;
                    break;
                }
            }

            if (!dbExists) {
                Info("Database does not exist. Creating it...");
                stmt.executeUpdate(createDatabaseQuery);
                Info("Database created successfully.");
            } else {
                Info("Database already exists.");
            }

            // Create the table if it doesn't exist
            stmt.executeUpdate(createTableQuery);
            Info("Table 'user_info' is ready.");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error occurred when creating database/table: " + e);
        }
    }

    public static void insertUserInfo(String firstName, String lastName, String postalCode) throws SQLException {
        String query = "INSERT INTO " + tableName + " (first_name, last_name, postal_code) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, postalCode);
            stmt.executeUpdate();
        }
    }

    private static boolean checkUserExists(String firstName, String lastName, String postalCode) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE first_name = ? AND last_name = ? AND postal_code = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, postalCode);
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();  // If data exists, it will return true
            rs.close();  // Close the ResultSet to prevent memory leaks
            return exists;
        }
    }

    public static void addUserAndConfirmRecord(String firstName, String lastName, String postalCode) throws SQLException {
        insertUserInfo(firstName, lastName, postalCode);
        boolean dataExists = checkUserExists(firstName, lastName, postalCode);
        if (dataExists) {
            Pass("User created on database.");
        } else {
            softFail("User not created, an error has occurred.");
        }
    }
}

