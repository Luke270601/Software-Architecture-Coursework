package com.lukescott.kwikmedical.Data;

import com.lukescott.kwikmedical.Business.CallOuts;
import com.lukescott.kwikmedical.Business.Patients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
/*
Author: Luke Scott
Data Last Edited: 23/11/2022
Class Summary: Handles all inserts into the database
such as ambulance requests and completed callout forms
 */
public class InsertDB {


    //Takes a randomly selected hospital and nhs number and inserts data into the request table
    public void insertAmbulanceRequest(int hospitalID, String nhsNumber) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connects to DB
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // SQL statement to insert data into specified table in the database
            String update = "INSERT INTO `ambulance request` (`Hospital ID`,`NHS Number`) " +
                    "VALUES ( ? , ? )";

            // Prepare statement to have values set to wild card character
            PreparedStatement preparedStatement = conn.prepareStatement(update);

            // Data to be inserted into prepared statement
            preparedStatement.setInt(1, hospitalID);
            preparedStatement.setString(2, nhsNumber);

            // Execute the statement
            preparedStatement.execute();
            // Release resources held by the statement
            preparedStatement.close();

            //Closes Connection
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

    //Uses passed callout object and inserts entry into the callout table
    public boolean insertCallOutReport(CallOuts callouts) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            // Build the INSERT statement
            String update = "INSERT INTO `callout reports` (`NHSNumber`, `Description`, `Time`, `Location`, `Action Taken`, `Call Time`) " +
                    "VALUES (? , ? , ? , ? , ? , ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(update);

            preparedStatement.setString(1, callouts.getNhsNumber());
            preparedStatement.setString(2, callouts.getDescription());
            preparedStatement.setString(3, callouts.getDateTime());
            preparedStatement.setString(4, callouts.getLocation());
            preparedStatement.setString(5, callouts.getActionTaken());
            preparedStatement.setInt(6, callouts.getCallTime());

            preparedStatement.execute();

            preparedStatement.close();

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

    // Adds pass patient object to insert it in the database
    public boolean insertPatient(Patients patient) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            String update = "INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`) " +
                    "VALUES (? , ? , ? , ? , ? , ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(update);

            preparedStatement.setString(1, patient.getNhsNumber());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getLastName());
            preparedStatement.setString(4, patient.getAddress());
            preparedStatement.setString(5, patient.getPostcode());
            preparedStatement.setString(6, patient.getMedCondition());

            preparedStatement.execute();

            preparedStatement.close();

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
