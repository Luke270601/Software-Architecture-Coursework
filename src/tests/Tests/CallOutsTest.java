import com.lukescott.kwikmedical.Business.Hospitals;
import com.lukescott.kwikmedical.Business.CallOuts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CallOutsTest {

    @Test
    void addIncident() {
    }

    @Test
    void createRequestList() {
        CallOuts callouts = new CallOuts("","","","","",0);
        Hospitals hospitals = new Hospitals(9872,"","","");
        String nhsNumber = "7276349012";
        List<String> list = callouts.createRequestList(hospitals.getHospitalID());

        Assertions.assertEquals(nhsNumber, list.get(0).split(" , ")[1]);
    }

    @Test
    void removeRequest() {
    }

    @Test
    void recordIncident() {
    }
}