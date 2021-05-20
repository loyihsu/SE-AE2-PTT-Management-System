package tests;

import org.junit.jupiter.api.Test;
import src.database.filedb.FileDB;
import src.database.interfaces.Database;
import src.database.interfaces.tables.TableFindable;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileDB {
    @Test
    void testFilterBySkill() throws FileNotFoundException {
        String path = new File("./tests/file.txt").getAbsolutePath();
        Database fdb = new FileDB(path);

        ArrayList<String> testcase = new ArrayList<String>();
        testcase.add("programming");
        testcase.add("databaseDesign");

        TableFindable<Staff> staff = fdb.getStaffTable();
        ArrayList<Staff> res = staff.findWithSkills(testcase);
        ArrayList<Staff> expectedResult = new ArrayList<Staff>();
        expectedResult.add(staff.find(3));

        for (int i = 0, length = res.size(); i < length; i++) {
            assertTrue(res.get(i).equals(expectedResult.get(i)));
        }
    }

    @Test
    void testStaffCleanerMethod() throws FileNotFoundException {
        String path = new File("./tests/file.txt").getAbsolutePath();
        Database database = new FileDB(path);

        Staff chris = database.getStaffTable().find(3);

        assertTrue(chris != null);

        ArrayList<Assignment> allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : database.getAssignmentTable().getTable()) {
            if (item.getStaff().equals(chris)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() > 0);

        database.cleanlyRemove(chris);

        assertTrue(database.getStaffTable().find(3) == null);

        allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : database.getAssignmentTable().getTable()) {
            if (item.getStaff().equals(chris)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() == 0);
    }

    @Test
    void testRequirementCleanerMethod() throws FileNotFoundException {
        String path = new File("./tests/file.txt").getAbsolutePath();
        Database database = new FileDB(path);

        Requirement programming = database.getRequirementTable().find(3);

        assertTrue(programming != null);

        ArrayList<Assignment> allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : database.getAssignmentTable().getTable()) {
            if (item.getRequirement().equals(programming)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() > 0);

        database.cleanlyRemove(programming);

        assertTrue(database.getRequirementTable().find(3) == null);

        allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : database.getAssignmentTable().getTable()) {
            if (item.getRequirement().equals(programming)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() == 0);
    }
}
