package Presentation;

import java.util.Scanner;

public class Client {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean sessionActive = true;

        while (sessionActive) {
            try {
                System.out.println("\nKwikMedical Health App\n");
                System.out.println("Please Select Account Type");
                System.out.println("1: Client Login");
                System.out.println("2: Hospital Login");
                System.out.println("3: Exit Program");

                System.out.print("\nChoice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> Login("Client");
                    case 2 -> Login("Hospital");
                    case 3 -> sessionActive = false;
                }

            } catch (Error e) {
                System.out.println("Invalid Choice Selected! Please try again");
            }
        }

        System.out.println("Thanks for using KwikMedical");
    }

    public static void Login(String accountType) {
        String email = "";
        String password = "";

        System.out.println("\nEnter Account Details");
        System.out.print("Email:");
        email = scanner.next();
        System.out.print("Password:");
        password = scanner.next();

        if (accountType == "Client") {
            ClientUI();
        }

        else{
            HospitalUI();
        }
    }

    public static void ClientUI() {
        boolean sessionActive = true;

        while (sessionActive) {
            try {
                System.out.println("\nKwikMedical Patient\n");
                System.out.println("1: Patient Entry");
                System.out.println("2: Exit");
                System.out.print("\nChoice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> PatientDetails();
                    case 2 -> sessionActive = false;
                }

            } catch (Error e) {
                System.out.println("Invalid Choice Selected! Please try again");
            }
        }

    }

    public static void HospitalUI() {
        boolean sessionActive = true;

        while (sessionActive) {
            try {
                System.out.println("\nKwikMedical Incident Record\n");
                System.out.println("1: Incident Entry");
                System.out.println("2: Exit");
                System.out.print("\nChoice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> IncidentRecord();
                    case 2 -> sessionActive = false;
                }

            } catch (Error e) {
                System.out.println("Invalid Choice Selected! Please try again");
            }
        }

    }

    public static void IncidentRecord(){
        String who;
        String what;
        String when;
        String where;
        String actionTaken;
        String callTime;

        System.out.println("\nEnter Incident Details");
        System.out.print("Patient Name: ");
        who = scanner.next();
        System.out.print("Incident Description: ");
        what = scanner.next();
        System.out.print("Time of Incident: ");
        when = scanner.next();
        System.out.print("Location of Incident: ");
        where = scanner.next();
        System.out.print("Actions taken to address situation: ");
        actionTaken = scanner.next();
        System.out.print("Time on call: ");
        callTime = scanner.next();
    }

    public static void PatientDetails() {

        String nhsNumber;
        String name;
        String address;
        String postCode;
        String condition;

        System.out.println("\nEnter Patient Details");
        System.out.print("NHS Number (if known): ");
        nhsNumber = scanner.next();
        System.out.print("Patient Name: ");
        name = scanner.next();
        System.out.print("Patient Address (Excluding Postcode): ");
        address = scanner.next();
        System.out.print("Patient PostCode: ");
        postCode = scanner.next();

        System.out.println("Requesting Ambulance...");
        System.out.println("Sending details to related hospital...");
    }
}