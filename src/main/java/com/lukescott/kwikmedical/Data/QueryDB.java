package com.lukescott.kwikmedical.Data;

import com.lukescott.kwikmedical.Business.Hospitals;
import com.lukescott.kwikmedical.Business.Patients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDB {

    // Returns a string containing patient details of a patient record matching the nhs number
    public String queryPatientRecords(String nhsNumber, String patient) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connects to DB
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");


            // SQL statement to query database and retrieve information
            String query = "SELECT * FROM `patient records` " +
                    "WHERE `NHSNumber` =  ?" ;

            // Prepare statement to have values set to wild card character
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            // Data to be passed to prepared statement for query
            preparedStatement.setString(1, nhsNumber);

            // Gets the results of a query
            ResultSet results = preparedStatement.executeQuery();

            // Loops through all retrieved data to produce a convert a patient record into a string
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
            preparedStatement.close();
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
        // Created patient string is returned to call location
        return patient;
    }


    // Creates list of every patient that exists to compare to inputted patient in related method
    public ArrayList<Patients> getAllPatients() {
        ArrayList<Patients> patients = new ArrayList<>();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            // A non prepared statement as no values are passed
            Statement statement = conn.createStatement();

            String query = "SELECT * FROM `patient records` ";

            // Queries the database to obtain all availiable data for patient records
            ResultSet results = statement.executeQuery(query);

            //Generates list of Patients objects
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

            statement.close();

            conn.close();


        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
        }
        return patients;
    }


    // Generates a list of the hospital based of data retrieved from the database
    public List<Hospitals> getHospitals(List<Hospitals> hospitalsList) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            Statement statement = conn.createStatement();

            String query = "SELECT * FROM `regional hospitals` " +
                    "ORDER BY Postcode";

            // Creates list of Hospitals object
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int hospitalID = (results.getInt("Hospital ID"));
                String name = (results.getString("Name"));
                String address = (results.getString("Address"));
                String postcode = (results.getString("Postcode"));
                Hospitals hospital = new Hospitals(hospitalID, name, address, postcode);
                hospitalsList.add(hospital);
            }

            statement.close();

            conn.close();
        } catch (
                ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
        } catch (
                SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
        }
        return hospitalsList;
    }

    // Generates a list of requests which have a hospital id matching the hospitalID
    public ArrayList<String> getHospitalRequests(ArrayList<String> requests, int hospitalID) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");


            String query = "SELECT * FROM `ambulance request` " +
                    "WHERE `Hospital ID` = ?";


            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, hospitalID);

            ResultSet results = preparedStatement.executeQuery();

            // Adds request to a list that match the hospital ID
            while (results.next()) {
                int id = (results.getInt("Request ID"));
                String nhsNumber = results.getString("NHS Number");


                requests.add(id + " , " + nhsNumber);
            }

            preparedStatement.close();

            conn.close();
        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
        }
        return requests;
    }

    // Generates a list of all recorded requests to use in a related method
    public ArrayList<String> getAllRequests() {
        ArrayList<String> requests = new ArrayList<>();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=root&password=");

            Statement statement = conn.createStatement();

            String query = "SELECT * FROM `ambulance request` ";

            ResultSet results = statement.executeQuery(query);

            // Generates a list of all requests
            while (results.next()) {
                int id = (results.getInt("Request ID"));
                String nhsNumber = results.getString("NHS Number");


                requests.add(id + " , " + nhsNumber);
            }

            statement.close();

            conn.close();
        } catch (ClassNotFoundException cnf) {
            System.err.println("Could not load driver");
            System.err.println(cnf.getMessage());
        } catch (SQLException sqe) {
            System.out.println("Error performing SQL Query");
            System.out.println(sqe.getMessage());
        }
        return requests;
    }
}
