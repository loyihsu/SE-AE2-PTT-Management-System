package src.database.interfaces.tables;

import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;

import java.io.IOException;

/**
 * The Database interface defining things all Database implementations should have.
 */
public interface Database {
    public void write() throws IOException;

    // ===============================================
    // Getters
    // ===============================================
    public TableFindable<Requirement> getRequirementTable();

    public TableFindable<Staff> getStaffTable();

    public TableFilterableByTypes<Assignment> getAssignmentTable();

    // ===============================================
    // Display Matrices
    // ===============================================
    public Object[][] getRequirementsDisplayMatrix();

    public Object[][] getStaffDisplayMatrix();
}