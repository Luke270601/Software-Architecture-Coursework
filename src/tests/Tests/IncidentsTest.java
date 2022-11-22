import com.lukescott.kwikmedical.Business.Hospitals;
import com.lukescott.kwikmedical.Business.Incidents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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