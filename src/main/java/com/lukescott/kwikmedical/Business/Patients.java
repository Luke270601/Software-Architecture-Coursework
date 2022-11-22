package com.lukescott.kwikmedical.Business;

import com.lukescott.kwikmedical.Data.InsertDB;
import com.lukescott.kwikmedical.Data.QueryDB;

import java.util.ArrayList;

public class Patients {

    //Creates instances of database classes to access their methods
    QueryDB queryDB = new QueryDB();
    InsertDB insertDB = new InsertDB();
    private String nhsNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String medCondition;

    public Patients(String nhsNumber, String firstName, String lastName, String address, String postcode, String medCondition) {
        this.nhsNumber = nhsNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.medCondition = medCondition;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMedCondition() {
        return medCondition;
    }

    public void setMedCondition(String medCondition) {
        this.medCondition = medCondition;
    }


    //Returns true when an ambulance request is successfully inserted into the database
    public void sendRequest(Hospitals hospital) {
        hospital.getHospital(hospital, false, 0);
        insertDB.insertAmbulanceRequest(hospital.getHospitalID(), getNhsNumber());
    }

    //Returns a string containing patient details using the passed nhs number
    public String getPatientDetails(String nhsNumber) {
        String patient = "";
        patient = queryDB.queryPatientRecords(nhsNumber, patient);
        return patient;
    }

    public boolean addPatient(Patients patient) {
        ArrayList<Patients> patients = queryDB.getAllPatients();
        int matches = 0;
        for (Patients patientInfo : patients) {
            if(patientInfo.getNhsNumber().equals(patient.getNhsNumber())){
                matches++;
            }
        }

        if(matches == 0) {
            if (insertDB.insertPatient(patient)) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}
