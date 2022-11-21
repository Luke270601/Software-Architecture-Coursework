package Tests;

import Business.Hospitals;
import Business.Incidents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IncidentsTest {

    @Test
    void addIncident() {
    }

    @Test
    void createRequestList() {
        Incidents incidents = new Incidents("","","","","",0);
        Hospitals hospitals = new Hospitals(9872,"","","");
        String nhsNumber = "7276349012";
        List<String> list = incidents.createRequestList(hospitals.getHospitalID());

        Assertions.assertEquals(nhsNumber, list.get(0).split(" , ")[1]);
    }

    @Test
    void removeRequest() {
    }

    @Test
    void recordIncident() {
    }
}