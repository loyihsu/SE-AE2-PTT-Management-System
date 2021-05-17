package database.filedb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import database.*;
import datatype.*;

public class FileDB implements Database {
    private String filepath;
    
    private ArrayList<Requirement> requirements = new ArrayList<Requirement>();
    private ArrayList<Staff> staff = new ArrayList<Staff>();

    /**
    The initialiser of the File Database.
    This initialiser throws a `FileNotFoundException`.
    @param path The path to the file to be read from and write to.
     */
    public FileDB(String path) throws FileNotFoundException {
        filepath = path;
        FileReader reader = new FileReader(path);
        
        ArrayList<String> reqs = new ArrayList<String>();
        ArrayList<String> people = new ArrayList<String>();
        
        Scanner scanner = new Scanner(reader);
        String temp = "";
        boolean flag = false;
        
        while (scanner.hasNextLine()) {
            temp = scanner.nextLine();
            if (temp.equals("")) {
                flag = true;
                continue;
            }
            if (!flag) {
                reqs.add(temp);
            } else {
                people.add(temp);
            }
        }
        
        Requirement.parse(reqs, requirements);
        Staff.parse(people, staff);
        scanner.close();
    }

    // ===============================================
    // Database
    // ===============================================
    /**
    The write function of the File Database.
    It will write the contents of the requirements and staff arraylists to the file specified in the initialiser.
    This method throws an `IOExcecption`.
     */
    public void write() throws IOException {
        FileWriter writer = new FileWriter(filepath);
        String output = "";
        for (Requirement requirement: requirements) {
            output += requirement.exportString() + "\n";
        }
        output += "\n";
        for (Staff person: staff) {;
            output += person.exportString() + "\n";
        }
        writer.write(output);
        writer.close();
    }

    // ===============================================
    // Getters and Setters
    // ===============================================
    // Requirements
    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }

    public void removeRequirement(Requirement requirement) {
        requirements.remove(requirement);
    }

    // Staff
    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void addStaff(Staff newStaff) {
        staff.add(newStaff);
    }

    public void removeStaff(Staff staffToRemove) {
        staff.remove(staffToRemove);
    }
}
