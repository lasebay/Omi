package a404_notfound.sourceappwater;

/**
 * Created by Zohra on 4/5/17.
 */


import com.google.android.gms.maps.model.LatLng;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import a404_notfound.sourceappwater.model.Report;

import a404_notfound.sourceappwater.model.RegisteredUser;
import java.util.Map;

import a404_notfound.sourceappwater.model.WorkerReport;

public class JunitTests {

    LatLng latitude2 = new LatLng(90,-90);
    Report report = new Report("Water", "Zohra Tabassum", latitude2 , "Bottled","Treatable-Clear", "08/30/97");

    WorkerReport reportworker = new WorkerReport("Water", "Zohra Tabassum", latitude2 , "Bottled","Treatable-Clear", "08/30/97", -10, -20);




    //Test to make sure you do
    //Zohra's Junit
    @Test(expected = IllegalArgumentException.class)
    public void testReporter() {
        report.setName("Lase");
        assertEquals("Lase", report.getReporter());
        report.setName(null);
    }

    //Josh's Junit
    @Test(expected = IllegalArgumentException.class)
      public void testWaterCondition(){
        report.setWaterCondition("Treatable-Muddy");
        assertEquals("Treatable-Muddy", report.getWaterCondition());
        report.setWaterCondition(null);
    }

    //Lase's Junit
    @Test(expected = IllegalArgumentException.class)
    public void testWaterType(){
        report.setWaterType("Bottled");
        assertEquals("Bottled", report.getWaterType());
        report.setWaterType(null);
    }


    //Jeffrey's JUnit

    @Test(expected = IllegalArgumentException.class)
    public void registeredUserTester() {
        final RegisteredUser user = new RegisteredUser();
        final String coordinates = user.getCoordinates();
        final String address = user.getAddress();
        final int login = user.getLoginAttemps();
        assertEquals(coordinates, "None");
        assertEquals(address, "None");
        assertEquals(login, 0);
        final Map<String, Object> ret = user.toMap();
        assertEquals("None", ret.get("coord"));
        user.setCoordinates(null);

    }


    // Michelle's Junit
    @Test(expected = IllegalArgumentException.class)
    public void testPPM(){
        reportworker.setPpm(10);
        assertEquals(10, reportworker.getPpm());
        reportworker.setPpm(-1);



    }



}
