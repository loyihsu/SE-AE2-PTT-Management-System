package tests;

import org.junit.jupiter.api.Test;
import src.database.filedb.tables.RequirementTable;
import src.database.filedb.tables.StaffTable;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.database.types.builder.RequirementBuilder;
import src.database.types.builder.StaffBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBuilders {
    @Test
    void testRequirementBuilder() {
        ArrayList<String> trainingsNeeded = new ArrayList<String>();
        trainingsNeeded.add("Programming");
        Requirement requirement1 = new Requirement(1, 2, 10, "01-01-2020", "03-30-2020", trainingsNeeded);
        Requirement requirement2 = RequirementBuilder
                .getInstance()
                .setId(1)
                .setCourseId(2)
                .setNumberOfStaffNeeded(10)
                .setTrainingsNeeded(trainingsNeeded)
                .setStartDate("01-01-2020")
                .setEndDate("03-30-2020")
                .build();
        assertTrue(requirement1.equals(requirement2));
    }

    @Test
    void testStaffBuilder() {
        Staff staff1 = new Staff(1, "Simon", "08-01-2020", new ArrayList<String>());
        Staff staff2 = StaffBuilder
                .getInstance()
                .setId(1)
                .setName("Simon")
                .setDateOfJoining("08-01-2020")
                .setTrainingsReceived(new ArrayList<String>())
                .build();
        assertTrue(staff1.equals(staff2));
    }

    @Test
    void RBtestParsingFromFileStrings() {
        String testcase = "";
        testcase += "1,1,10,01-01-2020,03-23-2020,programming databaseDesign\n";
        testcase += "2,2,5,01-01-2020,03-23-2020,databaseDesign\n";
        testcase += "3,2,4,01-01-2020,03-23-2020,programming\n";

        String startDate = "01-01-2020";
        String endDate = "03-23-2020";

        // Generate expeced results
        int[] courseId = {1, 2, 2};
        int[] needed = {10, 5, 4};
        String[][] trainings = {{"programming", "databaseDesign"}, {"databaseDesign"}, {"programming"}};

        ArrayList<Requirement> expectedResult = new ArrayList<Requirement>();
        for (int idx = 0; idx < 3; idx++) {
            ArrayList<String> training = new ArrayList<String>(Arrays.asList(trainings[idx]));
            Requirement requirement = RequirementBuilder
                    .getInstance()
                    .setId(idx + 1)
                    .setCourseId(courseId[idx])
                    .setNumberOfStaffNeeded(needed[idx])
                    .setTrainingsNeeded(training)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .build();
            expectedResult.add(requirement);
        }

        // Parse the testcase
        ArrayList<String> inputList = new ArrayList<String>();
        Scanner scanner = new Scanner(testcase);
        while (scanner.hasNextLine()) {
            inputList.add(scanner.nextLine());
        }
        RequirementTable table = new RequirementTable(inputList);

        // Compare results to the expected results
        for (int i = 0, length = table.getTable().size(); i < length; i++) {
            assertTrue(expectedResult.get(i).equals(table.getTable().get(i)));
        }
    }

    @Test
    void SBtestParsingFromFileStrings() {
        String testcase = "";
        testcase += "1,Leo,18-05-2019,programming\n";
        testcase += "2,Alice,18-06-2020,\n";
        testcase += "3,Chris,01-01-2019,databaseDesign programming\n";
        testcase += "4,Simon,01-01-2018,programming\n";

        // Generate expeced results
        String[] names = {"Leo", "Alice", "Chris", "Simon"};
        String[] joinDates = {"18-05-2019", "18-06-2020", "01-01-2019", "01-01-2018"};
        String[][] trainings = {{"programming"}, {}, {"databaseDesign", "programming"}, {"programming"}};

        ArrayList<Staff> expectedResult = new ArrayList<Staff>();
        for (int idx = 0; idx < 4; idx++) {
            ArrayList<String> trainingList = new ArrayList<String>(Arrays.asList(trainings[idx]));
            Staff person = StaffBuilder
                    .getInstance()
                    .setId(idx + 1)
                    .setName(names[idx])
                    .setDateOfJoining(joinDates[idx])
                    .setTrainingsReceived(trainingList)
                    .build();
            expectedResult.add(person);
        }

        // Parse the testcase
        ArrayList<String> inputList = new ArrayList<String>();
        Scanner scanner = new Scanner(testcase);
        while (scanner.hasNextLine()) {
            inputList.add(scanner.nextLine());
        }
        StaffTable table = new StaffTable(inputList);

        // Compare results to the expected results
        for (int i = 0, length = table.getTable().size(); i < length; i++) {
            assertTrue(expectedResult.get(i).equals(table.getTable().get(i)));
        }
    }
}
