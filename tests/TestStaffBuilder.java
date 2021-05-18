package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import src.datatype.*;
import src.datatype.builder.*;



public class TestStaffBuilder {
    @Test
    void testStaffBuilder() {
        Staff staff1 = new Staff(1, "Simon", "08-01-2020", new ArrayList<String>());
        Staff staff2 = StaffBuilder.getInstance()
                                   .setId(1)
                                   .setName("Simon")
                                   .setDateOfJoining("08-01-2020")
                                   .setTrainingsReceived(new ArrayList<String>())
                                   .build();
        assertTrue(staff1.equals(staff2));
    }
}
