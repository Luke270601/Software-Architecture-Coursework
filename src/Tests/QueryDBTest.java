package Tests;

import Business.Hospitals;
import Business.Incidents;
import Data.QueryDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueryDBTest {

    @Test
    void getHospitalRequests() {
        Hospitals hospitals = new Hospitals(9872,"","","");
        QueryDB queryDB = new QueryDB();
        ArrayList<String> list = new ArrayList<>();
        queryDB.getHospitalRequests(list, hospitals.getHospitalID());
        String nhsNumber = "7276349012";

        Assertions.assertEquals("7276349012", list.get(0).split(" , ")[1]);
    }
}