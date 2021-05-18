package tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import src.datatype.*;
import src.datatype.builder.*;

public class TestRequirementBuilder {
    @Test
    void testRequirementBuilder() {
        ArrayList<String> trainingsNeeded = new ArrayList<String>();
        trainingsNeeded.add("Programming");
        Requirement requirement1 = new Requirement(1, 2, 10, trainingsNeeded);
        Requirement requirement2 = RequirementBuilder.getInstance()
                                                     .setId(1)
                                                     .setCourseId(2)
                                                     .setNumberOfStaffNeeded(10)
                                                     .setTrainingsNeeded(trainingsNeeded)
                                                     .build();
        assertTrue(requirement1.equals(requirement2));
    }
    @Test
    void testParsingFromFileStrings() {
        String testcase = "";
        testcase += "1,1,10,programming databaseDesign\n";
        testcase += "2,2,5,databaseDesign\n";
        testcase += "3,2,4,programming\n";

        ArrayList<Requirement> expectedResult = new ArrayList<Requirement>();
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("programming");
        temp.add("databaseDesign");
        Requirement req = RequirementBuilder.getInstance()
                                            .setId(1)
                                            .setCourseId(1)
                                            .setNumberOfStaffNeeded(10)
                                            .setTrainingsNeeded(temp)
                                            .build();
        expectedResult.add(req);

        temp = new ArrayList<String>();
        temp.add("databaseDesign");
        req = RequirementBuilder.getInstance()
                                .setId(2)
                                .setCourseId(2)
                                .setNumberOfStaffNeeded(5)
                                .setTrainingsNeeded(temp)
                                .build();
        expectedResult.add(req);

        temp = new ArrayList<String>();
        temp.add("programming");
        req = RequirementBuilder.getInstance()
                                .setId(3)
                                .setCourseId(2)
                                .setNumberOfStaffNeeded(4)
                                .setTrainingsNeeded(temp)
                                .build();
        expectedResult.add(req);

        ArrayList<Requirement> result = new ArrayList<Requirement>();
        ArrayList<String> inputList = new ArrayList<String>();
        Scanner scanner = new Scanner(testcase);
        while (scanner.hasNextLine()) {
            inputList.add(scanner.nextLine());
        }
        Requirement.parse(inputList, result);

        for (int i = 0, length = result.size(); i < length; i++) {
            assertTrue(expectedResult.get(i).equals(result.get(i)));
        }

    }
}
