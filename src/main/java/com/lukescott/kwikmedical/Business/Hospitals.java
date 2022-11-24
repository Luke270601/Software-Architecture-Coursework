package com.lukescott.kwikmedical.Business;

import com.lukescott.kwikmedical.Data.QueryDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
Author: Luke Scott
Data Last Edited: 24/11/2022
Class Summary: Handle instances of hospital data as well as generating
a list of all hospital and on selecting a singular hospital
 */
public class Hospitals {

    // Creates instances of database classes to access their methods
    QueryDB queryDB = new QueryDB();

    private int hospitalID;
    private String name;
    private String address;
    private String postcode;


    private final List<Hospitals> hospitalsList = new ArrayList<Hospitals>();

    public Hospitals(int hospitalID, String name, String address, String postcode) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
    }


    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() { return postcode; }

    public void setPostcode(String postcode) { this.postcode = postcode; }

    // Returns randomly selected hospital whne requesting ambulance service but uses the selected hospital when generating requests
    // for the selected hospital
    public Hospitals getHospital(Hospitals hospital, boolean isHospital, int selection) {
        hospitalsList.clear();
        queryDB.getHospitals(hospitalsList);
        int randomNo = 0;

        // Chooses whether to use random number based off of isHospital
        Random random = new Random();
        if(!isHospital) {
           randomNo = random.nextInt(1, (hospitalsList.size()-1));
        }
        else {
            // Uses select from list to determine which hospital is selected
            randomNo = selection-1;
        }
        hospital.setHospitalID(hospitalsList.get(randomNo).hospitalID);
        hospital.setName(hospitalsList.get(randomNo).name);
        hospital.setAddress(hospitalsList.get(randomNo).address);
        hospital.setPostcode(hospitalsList.get(randomNo).postcode);
        return hospital;
    }

    // Generates list of hospitals and returns list of hospitals objects
    public List<Hospitals> generateHospitalList(){
        hospitalsList.clear();
        queryDB.getHospitals(hospitalsList);

        return hospitalsList;
    }

}
