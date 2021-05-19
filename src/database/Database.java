package src.database;

import java.io.IOException;

import src.database.types.*;

/**
The Database interface defining things all Database implementations should have.
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