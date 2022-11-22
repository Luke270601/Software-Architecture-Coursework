package com.lukescott.kwikmedical.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDB {

    //Removes request from the specified ID updating the database
    public boolean removeRequest(int requestID) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Set up keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // Create a new SQL statement
            Statement statement = conn.createStatement();

            // Build the INSERT statement
            String update = "DELETE FROM `ambulance request`  " +
                    "WHERE `Request ID` = " + requestID + "";

            // Execute the statement
            statement.executeUpdate(update);
            // Release resources held by the statement
            statement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
            return true;
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
            return false;
        } catch (
                SQLException sqe) {
            System.err.println("Error performing SQL Update");
            System.err.println(sqe.getMessage());
            System.exit(-1);
            return false;
        }
    }
}
