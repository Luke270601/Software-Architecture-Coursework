package com.lukescott.kwikmedical.Presentation;

import com.lukescott.kwikmedical.Business.CallOuts;
import com.lukescott.kwikmedical.Business.Hospitals;
import com.lukescott.kwikmedical.Business.Patients;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
Author: Luke Scott
Data Last Edited: 24/11/2022
Class Summary: Presents the gui elements
from the related form, handling input for
organisation callers as well as simulating callout
form input on a mobile device
 */
public class GUI extends JFrame {
    private JPanel mainMenu;
    private JTabbedPane paneMenu;
    private JButton searchPatients;
    private JTextField nhsNumber;
    private JComboBox hospitalSelect;
    private JTextField time;
    private JTextField calloutnhsNumber;
    private JTextField actionTaken;
    private JTextField callTime;
    private JTextArea description;
    private JTextArea location;
    private JButton confirmCalloutDetailsButton;
    private JPanel calloutPane;
    private JPanel orginisationPane;
    private JButton updateRequests;
    DefaultListModel requestModel = new DefaultListModel<>();
    DefaultListModel patientModel = new DefaultListModel<>();
    DefaultListModel calloutModel = new DefaultListModel<>();
    private JList requestList;
    private JList patientList;
    private JButton confirmPatient;
    private JTextField address;
    private JTextField medCondition;
    private JTextField firstLastName;
    private JTextField calloutMedCondition;
    private JPanel hospitalPane;
    private JButton updateCallouts;
    private JList calloutReports;


    Hospitals hospitals = new Hospitals(0, "", "", "");
    CallOuts callouts = new CallOuts(0, 0, "", "", "", "", "", 0);
    Patients patients = new Patients("", "", "", "", "", "");
    List<Hospitals> hospitalsList = hospitals.generateHospitalList();
    boolean listGenerated = false;

    public GUI() {

        //When button is pressed it displays the found patient in Jlist
        searchPatients.addActionListener(e -> findPatient());
        //When button is pressed a request is added to the database using patient details
        confirmPatient.addActionListener(e -> {
            PatientDetails();
            confirmPatient.setEnabled(false);
        });

        // When button is pressed it records the callout and updates the request list
        confirmCalloutDetailsButton.addActionListener(e -> {
            CalloutRecord();
        });
        // Changing to hospital pane adds hospitals to combobox and notes a list is generated to prevent further overlap when called
        paneMenu.addChangeListener(e -> {
            if (paneMenu.getSelectedIndex() == 1 && !listGenerated) {
                HospitalList();
                listGenerated = true;
            }
        });
        // When button is pressed the requests list is updated
        updateRequests.addActionListener(e -> getRequest());
        //Clears request list when other hospital is selected
        hospitalSelect.addItemListener(e -> {
            requestModel.clear();
            requestList.setModel(requestModel);
            calloutModel.clear();
            calloutReports.setModel(calloutModel);
        });
        // When a request is selected it fills the nhs number for the callout form
        requestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (requestList.getSelectedValue() != null) {
                    String number = requestList.getSelectedValue().toString().split(" ")[7].trim();
                    String[] patientInfo = patients.getPatientDetails(number).split(" , ");
                    String patient = "Sending patient details to ambulance\n" + "NHS Number: " + patientInfo[0] + "\nName: " + patientInfo[1] + " " + patientInfo[2]
                            + "\nAddress: " + patientInfo[3] + " , " + patientInfo[4] + "\nMedCondition: " + patientInfo[5];
                    JOptionPane.showMessageDialog(orginisationPane, patient);
                    patients.setNhsNumber(patientInfo[0]);
                    patients.setMedCondition(patientInfo[5]);
                    calloutnhsNumber.setText(number);
                    calloutMedCondition.setText(patientInfo[5]);
                } else {
                    calloutnhsNumber.setText("");
                    calloutMedCondition.setText("");
                }
            }
        });
        updateCallouts.addActionListener(e -> {
                    calloutModel.clear();
                    calloutReports.setModel(calloutModel);
                    CallOutList();
                }
        );
        calloutReports.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (calloutReports.getSelectedValue() != null) {
                    CalloutInfo(Integer.parseInt(calloutReports.getSelectedValue().toString().split(" ")[2]));
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setContentPane(gui.mainMenu);
        gui.setTitle("KwikMedical");
        gui.setSize(500, 400);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // Gets all hospitals from database and adds them to a combobox
    public void HospitalList() {
        List<Hospitals> hospitalsList = hospitals.generateHospitalList();
        hospitalSelect.removeAllItems();
        if (hospitalsList.size() > 0) {
            for (Hospitals hospital : hospitalsList) {
                hospitalSelect.addItem(hospital.getName());
            }
        } else {
            JOptionPane.showMessageDialog(hospitalPane, "No requests made");
        }
    }

    // Gets requests for selected Hospital
    public void CallOutList() {
        callouts.setHospitalID((hospitalsList.get(hospitalSelect.getSelectedIndex()).getHospitalID()));
        ArrayList<CallOuts> callOuts = callouts.getHospitalCallouts(callouts.getHospitalID());
        if (callOuts.size() > 0) {
            for (CallOuts callout : callOuts) {
                calloutModel.addElement("Callout ID: " + callout.getRequestID() + " NHS Number: " + callout.getNhsNumber());
                calloutReports.setModel(calloutModel);
            }
        } else {
            JOptionPane.showMessageDialog(hospitalPane, "No callouts recorded");
        }
    }

    public void CalloutInfo(int calloutID) {
        CallOuts callout = callouts.getCalloutInfo(calloutID);
        JOptionPane.showMessageDialog(hospitalPane, "Callout Information\n" + "NHS Number: " + callout.getNhsNumber() + "\nDescription: " + callout.getDescription()
                + "\nDate/Time: " + callout.getDateTime() + "\nLocation: " + callout.getLocation() + "\nAction Taken: " + callout.getActionTaken() + "\nCall Time: " + callout.getCallTime());
    }

    // Uses selected hospital to find requests made populating a list to use for callout records form
    public void getRequest() {
        requestModel.clear();
        requestList.setModel(requestModel);
        List<String> list = callouts.createRequestList(hospitalsList.get(hospitalSelect.getSelectedIndex()).getHospitalID());
        if (list.size() > 0) {
            for (String s : list) {
                requestModel.addElement("Request ID: " + s.split(" , ")[0] +
                        "\n   NHS Number: " + s.split(" , ")[1]);
                requestList.setModel(requestModel);
            }
        } else {
            if (hospitalPane.isShowing()) {
                JOptionPane.showMessageDialog(hospitalPane, "No requests for selected hospital");
            }
        }
    }

    // Gets form information and inserts callout into the database table and updates patient medical condition
    public void CalloutRecord() {

        callouts.setNhsNumber(calloutnhsNumber.getText());
        callouts.setDescription(description.getText());
        callouts.setDateTime(time.getText());
        callouts.setLocation(location.getText());
        callouts.setActionTaken(actionTaken.getText());
        callouts.setCallTime(Integer.parseInt(callTime.getText()));

        if (callouts.recordCallOut(callouts) && patients.updateMedCondition(new Patients(patients.getNhsNumber(), "", "",
                "", "", calloutMedCondition.getText()))) {
            String number = requestList.getSelectedValue().toString().split(" ")[2].trim();
            callouts.removeRequest(Integer.parseInt(number));
            JOptionPane.showMessageDialog(calloutPane, "Callout Form Completed");
            getRequest();
            calloutnhsNumber.setText("");
            description.setText("");
            time.setText("");
            location.setText("");
            actionTaken.setText("");
            callTime.setText("");
            calloutMedCondition.setText("");
        } else {
            JOptionPane.showMessageDialog(calloutPane, "Failed to record Callout, check form for errors");
        }
    }

    // Checks patient details and submits a request for callout
    public void PatientDetails() {
        if (callouts.requestsExists(patients.getNhsNumber())) {
            patients.sendRequest(hospitals);
            JOptionPane.showMessageDialog(orginisationPane, "Sending data to " + hospitals.getName());
            patientModel.clear();
            nhsNumber.setText("");
            firstLastName.setText("");
            address.setText("");
            medCondition.setText("");
        } else {
            JOptionPane.showMessageDialog(orginisationPane, "Request already made for patient");
            patientModel.clear();
            nhsNumber.setText("");
            firstLastName.setText("");
            address.setText("");
            medCondition.setText("");
        }
    }

    //Queries database (using NHS number) for patient information and display it in Jlist
    public void findPatient() {
        String patient = patients.getPatientDetails(nhsNumber.getText());
        patientModel.clear();

        if (patient != "") {
            patientList.setModel(patientModel);
            patientModel.addElement("NHS Number: " + patient.split(" . ")[0]);
            patientList.setModel(patientModel);
            patientModel.addElement("First Name: " + patient.split(" . ")[1]);
            patientList.setModel(patientModel);
            patientModel.addElement("Last Name: " + patient.split(" . ")[2]);
            patientList.setModel(patientModel);
            patientModel.addElement("Address: " + patient.split(" . ")[3]);
            patientList.setModel(patientModel);
            patientModel.addElement("Postcode: " + patient.split(" . ")[4]);
            patientList.setModel(patientModel);
            patientModel.addElement("Med Condition: " + patient.split(" . ")[5]);
            patientList.setModel(patientModel);

            patients.setNhsNumber(patient.split(" , ")[0]);
            patients.setPostcode(patient.split(" , ")[4]);

            confirmPatient.setEnabled(true);
        } else {
            int result = JOptionPane.showConfirmDialog(orginisationPane, "Patient not found, would you like to create new patient record", "Swing Tester",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                if (nhsNumber.getText().length() == 10 && !firstLastName.getText().isEmpty() && !address.getText().isEmpty() && !address.getText().isEmpty()) {
                    Patients patientInfo = new Patients(nhsNumber.getText(), firstLastName.getText().split(" ")[0], firstLastName.getText().split(" ")[1],
                            address.getText().split(" , ")[0], address.getText().split(" , ")[1], medCondition.getText());
                    if (patients.addPatient(patientInfo)) {
                        JOptionPane.showMessageDialog(orginisationPane, "Patient successfully added to database");
                        firstLastName.setText("");
                        address.setText("");
                        medCondition.setText("");
                        confirmPatient.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(orginisationPane, "Patient Form filled incorrectly");

                }
            } else if (result == JOptionPane.NO_OPTION) {
                confirmPatient.setEnabled(false);
            } else {
                confirmPatient.setEnabled(false);
            }
        }

    }
}
