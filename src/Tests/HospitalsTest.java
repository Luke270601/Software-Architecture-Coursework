package Tests;

import Business.Hospitals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HospitalsTest {

    @Test
    void getHospital() {
        Hospitals hospitals = new Hospitals(0,"","","");
        Hospitals hospital = new Hospitals(0,"","","");
        hospitals.getHospital(hospital, true, 1);
        String hospitalName = "Franks Hospital";

        Assertions.assertEquals(hospitalName, hospital.getName());
    }

    @Test
    void generateHospitalList() {
        Hospitals hospitals = new Hospitals(0,"","","");
        List<Hospitals> hospitalsList = hospitals.generateHospitalList();
        String hospitalName = "Mura Foundation";

        Assertions.assertEquals(hospitalName, hospitalsList.get(4).getName());
    }
}