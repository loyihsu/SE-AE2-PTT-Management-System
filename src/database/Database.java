package src.database;

import java.util.ArrayList;
import java.io.IOException;

import src.datatype.*;

/**
The Database interface defining things all Database implementations should have.
*/
public interface Database {
    public void write() throws IOException;
    
    // ===============================================
    // Getters
    // ===============================================
    public Requirement getRequirement(int id);
    public ArrayList<Requirement> getRequirements();
    public Staff getSpecificStaff(int id);
    public ArrayList<Staff> getStaff();
    public ArrayList<Staff> getStaffWith(ArrayList<String> skills);

    // ===============================================
    // Setters
    // ===============================================
    public void addRequirement(Requirement requirement);
    public void removeRequirement(Requirement requirement);
    public void addStaff(Staff newStaff);
    public void removeStaff(Staff staffToRemove);
}