package com.lukescott.kwikmedical.Data;

import com.lukescott.kwikmedical.Business.Hospitals;
import com.lukescott.kwikmedical.Business.Patients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDB {

    //Returns a string containing patient details of a patient record matching the nhs number
    public String queryPatientRecords(String nhsNumber, String patient) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Next we create a statement to access the database
            Statement statement = conn.createStatement();

            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM `patient records` " +
                    "WHERE `NHSNumber` = '"+ nhsNumber +"'" ;
            // And then get the results from executing the query
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String nhSNumber = results.getString("NHSNumber");
                String firstName = results.getString("First Name");
                String lastName = results.getString("Last Name");
                String address = results.getString("Address");
                String postcode = results.getString("Postcode");
                String Med = results.getString("MedCondition");
                patient = nhSNumber + " , " + firstName + " , " + lastName + " , " + address + " , " + postcode + " , " + Med;
            }
            // Release resources held by statement
            statement.close();
            // Release resources held by DB connection
            conn.close();


        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
            System.exit(-1);
        }
        return patient;
    }

    public ArrayList<Patients> getAllPatients() {
        ArrayList<Patients> patients = new ArrayList<>();
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Next we create a statement to access the database
            Statement statement = conn.createStatement();

            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM `patient records` ";
            // And then get the results from executing the query
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String nhsNumber = results.getString("NHSNumber");
                String firstName = results.getString("First Name");
                String lastName = results.getString("Last Name");
                String address = results.getString("Address");
                String postcode = results.getString("Postcode");
                String Med = results.getString("MedCondition");
                Patients patient = new Patients(nhsNumber, firstName, lastName, address, postcode, Med);
                patients.add(patient);
            }
            // Release resources held by statement
            statement.close();
            // Release resources held by DB connection
            conn.close();


        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
            System.exit(-1);
        }
        return patients;
    }


    //Queries the hospital table and adds the to a list of hospitals instances and returns the list
    public List<Hospitals> getHospitals(List<Hospitals> hospitalsList) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Next we create a statement to access the database
            Statement statement = conn.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM `regional hospitals` " +
                    "ORDER BY Postcode";
            // And then get the results from executing the query
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int hospitalID = (results.getInt("Hospital ID"));
                String name = (results.getString("Name"));
                String address = (results.getString("Address"));
                String postcode = (results.getString("Postcode"));
                Hospitals hospital = new Hospitals(hospitalID, name, address, postcode);
                hospitalsList.add(hospital);
            }
            // Release resources held by statement
            statement.close();
            // Release resources held by DB connection
            conn.close();
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
        } catch (
                SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
            System.exit(-1);
        }
        return hospitalsList;
    }

    //Gets requests which have a hospital id matching the passed parameter and returns an array list of strings
    public ArrayList<String> getHospitalRequests(ArrayList<String> requests, int hospitalID) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Next we create a statement to access the database
            Statement statement = conn.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM `ambulance request` " +
                    "WHERE `Hospital ID` = '" + hospitalID + "'";
            // And then get the results from executing the query
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int id = (results.getInt("Request ID"));
                String nhsNumber = results.getString("NHS Number");


                requests.add(id + " , " + nhsNumber);
            }
            // Release resources held by statement
            statement.close();
            // Release resources held by DB connection
            conn.close();
        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
            System.exit(-1);
        }
        return requests;
    }

    public ArrayList<String> getAllRequests() {
        ArrayList<String> requests = new ArrayList<>();
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // First we need to establish a connection to the database
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");
            // Next we create a statement to access the database
            Statement statement = conn.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM `ambulance request` ";
            // And then get the results from executing the query
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int id = (results.getInt("Request ID"));
                String nhsNumber = results.getString("NHS Number");


                requests.add(id + " , " + nhsNumber);
            }
            // Release resources held by statement
            statement.close();
            // Release resources held by DB connection
            conn.close();
        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
            System.exit(-1);
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
            System.exit(-1);
        }
        return requests;
    }
}
