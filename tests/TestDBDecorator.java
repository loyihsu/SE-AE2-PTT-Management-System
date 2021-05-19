package tests;

import org.junit.jupiter.api.Test;
import src.database.DatabaseDecorator;
import src.database.filedb.FileDB;
import src.database.interfaces.tables.Database;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDBDecorator {
    @Test
    void testStaffCleanerMethod() throws FileNotFoundException {
        String path = new File("./tests/file.txt").getAbsolutePath();
        Database db = new FileDB(path);
        DatabaseDecorator decorator = new DatabaseDecorator(db);

        Staff chris = decorator.getDatabase().getStaffTable().find(3);

        assertTrue(chris != null);

        ArrayList<Assignment> allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : decorator.getDatabase().getAssignmentTable().getTable()) {
            if (item.getStaff().equals(chris)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() > 0);

        decorator.cleanlyRemoveStaff(chris);

        assertTrue(decorator.getDatabase().getStaffTable().find(3) == null);

        allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : decorator.getDatabase().getAssignmentTable().getTable()) {
            if (item.getStaff().equals(chris)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() == 0);
    }

    @Test
    void testRequirementCleanerMethod() throws FileNotFoundException {
        String path = new File("./tests/file.txt").getAbsolutePath();
        Database db = new FileDB(path);
        DatabaseDecorator decorator = new DatabaseDecorator(db);

        Requirement programming = decorator.getDatabase().getRequirementTable().find(3);

        assertTrue(programming != null);

        ArrayList<Assignment> allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : decorator.getDatabase().getAssignmentTable().getTable()) {
            if (item.getRequirement().equals(programming)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() > 0);

        decorator.cleanlyRemoveRequirement(programming);

        assertTrue(decorator.getDatabase().getRequirementTable().find(3) == null);

        allRelatedAssignments = new ArrayList<Assignment>();

        for (Assignment item : decorator.getDatabase().getAssignmentTable().getTable()) {
            if (item.getRequirement().equals(programming)) {
                allRelatedAssignments.add(item);
            }
        }

        assertTrue(allRelatedAssignments.size() == 0);
    }
}
