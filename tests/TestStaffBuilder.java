package tests;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import src.database.filedb.tables.StaffTable;
import src.database.types.*;
import src.database.types.builder.*;

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

    @Test
    void testParsingFromFileStrings() {
        String testcase = "";
        testcase += "1,Leo,18-05-2019,programming\n";
        testcase += "2,Alice,18-06-2020,\n";
        testcase += "3,Chris,01-01-2019,databaseDesign programming\n";
        testcase += "4,Simon,01-01-2018,programming\n";

        ArrayList<Staff> expectedResult = new ArrayList<Staff>();
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("programming");
        Staff person = StaffBuilder.getInstance()
                                   .setId(1)
                                   .setName("Leo")
                                   .setDateOfJoining("18-05-2019")
                                   .setTrainingsReceived(temp)
                                   .build();
        expectedResult.add(person);

        temp = new ArrayList<String>();
        person = StaffBuilder.getInstance()
                      .setId(2)
                      .setName("Alice")
                      .setDateOfJoining("18-06-2020")
                      .setTrainingsReceived(temp)
                      .build();
        expectedResult.add(person);

        temp = new ArrayList<String>();
        temp.add("databaseDesign");
        temp.add("programming");
        person = StaffBuilder.getInstance()
                             .setId(3)
                             .setName("Chris")
                             .setDateOfJoining("01-01-2019")
                             .setTrainingsReceived(temp)
                             .build();
        expectedResult.add(person);

        temp = new ArrayList<String>();
        temp.add("programming");
        person = StaffBuilder.getInstance()
                             .setId(4)
                             .setName("Simon")
                             .setDateOfJoining("01-01-2018")
                             .setTrainingsReceived(temp)
                             .build();
        expectedResult.add(person);

        ArrayList<String> inputList = new ArrayList<String>();
        Scanner scanner = new Scanner(testcase);
        while (scanner.hasNextLine()) {
            inputList.add(scanner.nextLine());
        }
        StaffTable table = new StaffTable(inputList);

        for (int i = 0, length = table.getTable().size(); i < length; i++) {
            assertTrue(expectedResult.get(i).equals(table.getTable().get(i)));
        }

    }
}
