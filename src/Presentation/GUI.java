package Presentation;

import Business.Hospitals;
import Business.Incidents;
import Business.Patients;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.lang.reflect.Array;
import java.util.List;

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
    private JPanel hospitalPane;
    private JPanel orginisationPane;
    private JButton updateRequests;
    DefaultListModel requestModel = new DefaultListModel<>();
    DefaultListModel patientModel = new DefaultListModel<>();
    private JList requestList;
    private JList patientList;
    private JButton confirmPatient;
    private JTextField address;
    private JTextField medCondition;
    private JTextField firstLastName;


    Hospitals hospitals = new Hospitals(0, "", "", "");
    Incidents incidents = new Incidents("", "", "", "", "", 0);
    Patients patients = new Patients("", "", "", "", "", "");
    boolean listGenerated = false;

    public GUI() {

        //When button is pressed it displays the found patient in Jlist
        searchPatients.addActionListener(e -> findPatient());
        //When button is pressed a request is added to the database using patient details
        confirmPatient.addActionListener(e -> {
            PatientDetails();
            confirmPatient.setEnabled(false);
        });

        //When button is pressed it records the incident and updates the request list
        confirmCalloutDetailsButton.addActionListener(e -> {
            IncidentRecord();
            getRequest();
        });
        //Changing to hospital pane adds hospitals to combobox and notes a list is generated to prevent further overlap when called
        paneMenu.addChangeListener(e -> {
            if (paneMenu.getSelectedIndex() == 1 && !listGenerated) {
                HospitalList();
                listGenerated = true;
            }
        });
        //When button is pressed the requests list is updated
        updateRequests.addActionListener(e -> getRequest());
        //Clears request list when other hospital is selected
        hospitalSelect.addItemListener(e -> {
            requestModel.clear();
            requestList.setModel(requestModel);
        });
        //When a request is selected it fills the nhs number for the incident form
        requestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (requestList.getSelectedValue() != null) {
                    String number = requestList.getSelectedValue().toString().split(" ")[7].trim();
                    String[] patientInfo = patients.getPatientDetails(number).split(" , ");
                    String patient = "NHS Number: " + patientInfo[0] + "\nName: " + patientInfo[1] + " " + patientInfo[2]
                            + "\nAddress: " + patientInfo[3] + " , " + patientInfo[4] + "\nMedCondition: " + patientInfo[5];
                    JOptionPane.showMessageDialog(orginisationPane, patient);
                    calloutnhsNumber.setText(number);
                } else {
                    calloutnhsNumber.setText("");
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


    //Gets all hospitals from database and adds them to a combobox
    public void HospitalList() {
        List<Hospitals> hospitalsList = hospitals.generateHospitalList();
        hospitalSelect.removeAllItems();

        for (Hospitals hospital : hospitalsList) {
            hospitalSelect.addItem(hospital.getName());
        }
    }

    //Uses selected hospital to find requests made populating a list to use for incident records form
    public void getRequest() {
        requestModel.clear();
        requestList.setModel(requestModel);
        List<Hospitals> hospitalsList = hospitals.generateHospitalList();
        List<String> list = incidents.createRequestList(hospitalsList.get(hospitalSelect.getSelectedIndex()).getHospitalID());
        if (list.size() > 0) {
            for (String s : list) {
                requestModel.addElement("Request ID: " + s.split(" , ")[0] +
                        "\n   NHS Number: " + s.split(" , ")[1]);
                requestList.setModel(requestModel);
            }
        } else {
            JOptionPane.showMessageDialog(hospitalPane, "No requests for selected hospital");
        }
    }

    //Gets form information and inserts incident into the database table
    public void IncidentRecord() {

        incidents.setNhsNumber(calloutnhsNumber.getText());
        incidents.setDescription(description.getText());
        incidents.setDateTime(time.getText());
        incidents.setLocation(location.getText());
        incidents.setActionTaken(actionTaken.getText());
        incidents.setCallTime(Integer.parseInt(callTime.getText()));

        if (incidents.recordIncident(incidents)) {
            String number = requestList.getSelectedValue().toString().split(" ")[2].trim();
            incidents.removeRequest(Integer.parseInt(number));
            JOptionPane.showMessageDialog(hospitalPane, "Sending callout details to mobile device");
            calloutnhsNumber.setText("");
            description.setText("");
            time.setText("");
            location.setText("");
            actionTaken.setText("");
            callTime.setText("");
        } else {
            JOptionPane.showMessageDialog(hospitalPane, "Failed to record incident, check form for errors");
        }
    }

    //Checks patient details and submits a request for callout
    public void PatientDetails() {
        if (incidents.requestsExists(patients.getNhsNumber())) {
            patients.sendRequest(hospitals);
            JOptionPane.showMessageDialog(orginisationPane, "Sending data to " + hospitals.getName());
            patientModel.clear();
            nhsNumber.setText("");
        } else {
            JOptionPane.showMessageDialog(orginisationPane, "Request already made for patient");
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
