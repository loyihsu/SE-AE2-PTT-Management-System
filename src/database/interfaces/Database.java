package src.database.interfaces;

import src.database.interfaces.tables.TableFilterableByAssignmentElements;
import src.database.interfaces.tables.TableFindable;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.database.types.interfaces.AssignmentElement;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Database interface defining things all Database implementations should have.
 */
public interface Database {
    void write() throws IOException;

    // ===============================================
    // Getters
    // ===============================================
    TableFindable<Requirement> getRequirementTable();

    TableFindable<Staff> getStaffTable();

    TableFilterableByAssignmentElements<Assignment> getAssignmentTable();

    // ===============================================
    // Cleaner Method
    // ===============================================
    void cleanlyRemove(AssignmentElement item);

    // ===============================================
    // Filter Method
    // ===============================================
    ArrayList<Requirement> getRequirementsWithoutEnoughPeople();

    // ===============================================
    // Display Matrices
    // ===============================================
    Object[][] getRequirementsDisplayMatrix();

    Object[][] getStaffDisplayMatrix();
}