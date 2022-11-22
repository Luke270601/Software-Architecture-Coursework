package com.lukescott.kwikmedical.Data;

import com.lukescott.kwikmedical.Business.Incidents;
import com.lukescott.kwikmedical.Business.Patients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class InsertDB {


    //Takes a randomly selected hospital and nhs number and inserts data into the request table
    public void insertAmbulanceRequest(int hospitalID, String nhsNumber) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Set up keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // Build the INSERT statement
            String update = "INSERT INTO `ambulance request` (`Hospital ID`,`NHS Number`) " +
                    "VALUES ( ? , ? )";

            // Create a new SQL statement
            PreparedStatement preparedStatement = conn.prepareStatement(update);

            //passes set data to wildcard
            preparedStatement.setInt(1, hospitalID);
            preparedStatement.setString(2, nhsNumber);
            // Execute the statement
            preparedStatement.execute();
            // Release resources held by the statement
            preparedStatement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
        } catch (
                SQLException sqe) {
            System.err.println("Error performing SQL Update");
            System.err.println(sqe.getMessage());
        }
    }

    //Uses passed incident object and inserts entry into the Incident table
    public boolean insertIncidentReport(Incidents incidents) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Set up keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // Build the INSERT statement
            String update = "INSERT INTO `incident reports` (`NHSNumber`, `Description`, `Time`, `Location`, `Action Taken`, `Call Time`) " +
                    "VALUES (? , ? , ? , ? , ? , ?)";

            // Create a new SQL statement
            PreparedStatement preparedStatement = conn.prepareStatement(update);

            //passes set data to wildcard
            preparedStatement.setString(1, incidents.getNhsNumber());
            preparedStatement.setString(2, incidents.getDescription());
            preparedStatement.setString(3, incidents.getDateTime());
            preparedStatement.setString(4, incidents.getLocation());
            preparedStatement.setString(5, incidents.getActionTaken());
            preparedStatement.setInt(6, incidents.getCallTime());
            // Execute the statement
            preparedStatement.execute();
            // Release resources held by the statement
            preparedStatement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
            return true;
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            return false;
        } catch (
                SQLException sqe) {
            System.err.println("Error performing SQL Update");
            System.err.println(sqe.getMessage());
            return false;
        }
    }

    //Uses passed incident object and inserts entry into the Incident table
    public boolean insertPatient(Patients patient) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Set up keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // Build the INSERT statement
            String update = "INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`) " +
                    "VALUES (? , ? , ? , ? , ? , ?)";

            // Create a new SQL statement
            PreparedStatement preparedStatement = conn.prepareStatement(update);

            //passes set data to wildcard
            preparedStatement.setString(1, patient.getNhsNumber());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getLastName());
            preparedStatement.setString(4, patient.getAddress());
            preparedStatement.setString(5, patient.getPostcode());
            preparedStatement.setString(6, patient.getMedCondition());
            // Execute the statement
            preparedStatement.execute();
            // Release resources held by the statement
            preparedStatement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
            return true;
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            return false;
        } catch (
                SQLException sqe) {
            System.err.println("Error performing SQL Update");
            System.err.println(sqe.getMessage());
            return false;
        }
    }
}
