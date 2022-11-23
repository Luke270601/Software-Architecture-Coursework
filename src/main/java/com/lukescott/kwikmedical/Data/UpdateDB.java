package com.lukescott.kwikmedical.Data;

import java.sql.*;

public class UpdateDB {

    //Removes request from the specified ID updating the database
    public boolean removeRequest(int requestID) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            String update = "DELETE FROM `ambulance request`  " +
                    "WHERE `Request ID` = ?";

            // Prepare statement to have values set to wild card character
            PreparedStatement preparedStatement = conn.prepareStatement(update);

            // Data to be used to remove request with matching ID
            preparedStatement.setInt(1, requestID);

            // Executes the statement
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

    //Updates medical condition of patient from callout form
    public boolean updateMedCondition(String nhsNumber, String medCondition) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            String update = "UPDATE `patient records` SET `MedCondition` = ?" +
                    " WHERE `patient records`.`NHSNumber` = ? ";


            PreparedStatement preparedStatement = conn.prepareStatement(update);

            preparedStatement.setString(1, medCondition);
            preparedStatement.setString(2, nhsNumber);

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
