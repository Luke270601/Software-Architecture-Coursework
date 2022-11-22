import com.lukescott.kwikmedical.Business.Patients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PatientsTest {

    @Test
    void getPatientDetails() {
        Patients patients = new Patients("7276349012","","","", "", "");
        String expectedName = "Holly";
        String actualName = patients.getPatientDetails(patients.getNhsNumber()).split(" , ")[1];
        Assertions.assertEquals(expectedName, actualName);
    }
}