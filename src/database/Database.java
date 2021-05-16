package database;

import java.util.ArrayList;
import java.io.IOException;

import datatype.*;

/**
The Database interface defining things all Database implementations should have.
*/
public interface Database {
    public void write() throws IOException;
    
    // ===============================================
    // Getters
    // ===============================================
    public ArrayList<Requirement> getRequirements();
    public ArrayList<Staff> getStaff();
    
    // ===============================================
    // Setters
    // ===============================================
    public void addRequirement(Requirement requirement);
    public void removeRequirement(Requirement requirement);
    public void addStaff(Staff newStaff);
    public void removeStaff(Staff staffToRemove);
}