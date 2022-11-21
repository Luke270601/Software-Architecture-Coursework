package Data;

import Business.Incidents;
import Business.Patients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

            // Create a new SQL statement
            Statement statement = conn.createStatement();

            // Build the INSERT statement
            String update = "INSERT INTO `ambulance request` (`Hospital ID`,`NHS Number`) " +
                    "VALUES ('" + hospitalID + "','" + nhsNumber + "')";

            // Execute the statement
            statement.executeUpdate(update);
            // Release resources held by the statement
            statement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
            System.out.println("Sending Request");
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
        } catch (
                SQLException sqe) {
            System.err.println("Error performing SQL Update");
            System.err.println(sqe.getMessage());
            System.exit(-1);
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

            // Create a new SQL statement
            Statement statement = conn.createStatement();

            // Build the INSERT statement
            String update = "INSERT INTO `incident reports` (`NHSNumber`, `Description`, `Time`, `Location`, `Action Taken`, `Call Time`) " +
                    "VALUES ('" + incidents.getNhsNumber() + "', '" + incidents.getDescription() + "'," +
                           " '" + incidents.getDateTime() + "', '" + incidents.getLocation() + "', '"
                                + incidents.getActionTaken() + "', ' " + incidents.getCallTime() + " ')";

            // Execute the statement
            statement.executeUpdate(update);
            // Release resources held by the statement
            statement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
            System.out.println("Recording Saved");
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

            // Create a new SQL statement
            Statement statement = conn.createStatement();

            // Build the INSERT statement
            String update = "INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`) " +
                    "VALUES ('" + patient.getNhsNumber() + "', '" + patient.getFirstName() + "'," +
                    " '" + patient.getLastName() + "', '" + patient.getAddress() + "', '"
                    + patient.getPostcode() + "', ' " + patient.getMedCondition() + " ')";

            // Execute the statement
            statement.executeUpdate(update);
            // Release resources held by the statement
            statement.close();
            // Release resources held by the connection.  This also ensures that the INSERT completes
            conn.close();
            System.out.println("Recording Saved");
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
