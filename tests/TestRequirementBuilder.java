package tests;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import src.database.filedb.tables.RequirementTable;
import src.datatype.*;
import src.datatype.builder.*;

public class TestRequirementBuilder {
    @Test
    void testRequirementBuilder() {
        ArrayList<String> trainingsNeeded = new ArrayList<String>();
        trainingsNeeded.add("Programming");
        Requirement requirement1 = new Requirement(1, 2, 10, "01-01-2020", "03-30-2020", trainingsNeeded);
        Requirement requirement2 = RequirementBuilder.getInstance()
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
    void testParsingFromFileStrings() {
        String testcase = "";
        testcase += "1,1,10,01-01-2020,03-23-2020,programming databaseDesign\n";
        testcase += "2,2,5,01-01-2020,03-23-2020,databaseDesign\n";
        testcase += "3,2,4,01-01-2020,03-23-2020,programming\n";

        String startDate = "01-01-2020";
        String endDate = "03-23-2020";

        ArrayList<Requirement> expectedResult = new ArrayList<Requirement>();
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("programming");
        temp.add("databaseDesign");
        Requirement req = RequirementBuilder.getInstance()
                                            .setId(1)
                                            .setCourseId(1)
                                            .setNumberOfStaffNeeded(10)
                                            .setTrainingsNeeded(temp)
                                            .setStartDate(startDate)
                                            .setEndDate(endDate)
                                            .build();
        expectedResult.add(req);

        temp = new ArrayList<String>();
        temp.add("databaseDesign");
        req = RequirementBuilder.getInstance()
                                .setId(2)
                                .setCourseId(2)
                                .setNumberOfStaffNeeded(5)
                                .setTrainingsNeeded(temp)
                                .setStartDate(startDate)
                                .setEndDate(endDate)
                                .build();
        expectedResult.add(req);

        temp = new ArrayList<String>();
        temp.add("programming");
        req = RequirementBuilder.getInstance()
                                .setId(3)
                                .setCourseId(2)
                                .setNumberOfStaffNeeded(4)
                                .setTrainingsNeeded(temp)
                                .setStartDate(startDate)
                                .setEndDate(endDate)
                                .build();
        expectedResult.add(req);

        ArrayList<String> inputList = new ArrayList<String>();
        Scanner scanner = new Scanner(testcase);
        while (scanner.hasNextLine()) {
            inputList.add(scanner.nextLine());
        }
        RequirementTable table = new RequirementTable(inputList);

        for (int i = 0, length = table.getTable().size(); i < length; i++) {
            assertTrue(expectedResult.get(i).equals(table.getTable().get(i)));
        }
    }
}
