import com.lukescott.kwikmedical.Business.Hospitals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        String hospitalName = "Franks Hospital";

        Assertions.assertEquals(hospitalName, hospitalsList.get(0).getName());
    }
}