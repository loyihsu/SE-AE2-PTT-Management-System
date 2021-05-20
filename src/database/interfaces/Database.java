package src.database.interfaces;

import src.database.interfaces.tables.TableFilterableByTypes;
import src.database.interfaces.tables.TableFindable;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;

import java.io.IOException;
import java.util.ArrayList;

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
    // Cleaner Methods
    // ===============================================
    public void cleanlyRemoveStaff(Staff staff);

    public void cleanlyRemoveRequirement(Requirement requirement);

    public ArrayList<Requirement> getRequirementsWithoutEnoughPeople();

    // ===============================================
    // Display Matrices
    // ===============================================
    public Object[][] getRequirementsDisplayMatrix();

    public Object[][] getStaffDisplayMatrix();
}