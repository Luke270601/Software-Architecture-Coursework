package com.lukescott.kwikmedical.Business;


import com.lukescott.kwikmedical.Data.InsertDB;
import com.lukescott.kwikmedical.Data.QueryDB;
import com.lukescott.kwikmedical.Data.UpdateDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CallOuts {

    //Creates instances of database classes to access their methods
    QueryDB queryDB = new QueryDB();
    UpdateDB updateDB = new UpdateDB();
    InsertDB insertDB = new InsertDB();
    private String nhsNumber;
    private String description;
    private String dateTime;
    private String Location;
    private String actionTaken;
    private int callTime;

    public CallOuts(String nhsNumber, String description, String dateTime, String location, String actionTaken, int callTime) {
        this.nhsNumber = nhsNumber;
        this.description = description;
        this.dateTime = dateTime;
        this.Location = location;
        this.actionTaken = actionTaken;
        this.callTime = callTime;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public int getCallTime() {
        return callTime;
    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    //Returns list of requests from specified hospital using hospitalID
    public List<String> createRequestList(int hospitalID) {
        ArrayList<String> list = new ArrayList<>();
        queryDB.getHospitalRequests(list, hospitalID);
        return list;
    }

    //Checks if a request with matching NHS number already exists
    public boolean requestsExists(String nhsNumber){
        int matches = 0;
        ArrayList<String> requests = queryDB.getAllRequests();

        // Loops through all requests to determine whether the input matches
        for (String request: requests) {
            if(Objects.equals(request.split(" , ")[1], nhsNumber)){
                matches++;
            }
        }
        if(matches == 0){
            return true;
        }
        else {
            return false;
        }
    }

    //Returns true when remove request is completed correctly
    public boolean removeRequest(int requestID){
        return updateDB.removeRequest(requestID);
    }


    //Returns true when an callout is successfully recorded
    public boolean recordCallOut(CallOuts callouts){
        return insertDB.insertCallOutReport(callouts);
    }
}
