package javafxapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to handle MySQL database connection
 */
public class database {

    public static Connection connectDb() {
        try {
            // Load the MySQL JDBC driver (for older versions; for MySQL 8+, use com.mysql.cj.jdbc.Driver)
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the database
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost/java_hotel_db", "root", ""); //usernaem root passowrd null ''

            // Return the connection
            return connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Print error details if connection fails
        }

        return null; // Return null if connection fails
    }
}
