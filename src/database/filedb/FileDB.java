package src.database.filedb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import src.database.*;
import src.datatype.*;

public class FileDB implements Database {
    private String filepath;
    
    private ArrayList<Requirement> requirements = new ArrayList<Requirement>();
    private ArrayList<Staff> staff = new ArrayList<Staff>();
    private ArrayList<Assignment> assignments = new ArrayList<Assignment>();

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
        ArrayList<String> ass = new ArrayList<String>();
        
        Scanner scanner = new Scanner(reader);
        String temp = "";
        int mode = 0;
        
        while (scanner.hasNextLine()) {
            temp = scanner.nextLine();
            if (temp.equals("")) {
                mode++;
                continue;
            }
            if (mode == 0) {
                reqs.add(temp);
            } else if (mode == 1) {
                people.add(temp);
            } else {
                ass.add(temp);
            }
        }
        
        Requirement.parse(reqs, requirements);
        Staff.parse(people, staff);

        for (String line: ass) {
            scanner = new Scanner(line).useDelimiter(",");
            int sid = scanner.nextInt();
            int rid = scanner.nextInt();
            assign(sid, rid);
        }
        
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
            output += requirement.toString() + "\n";
        }
        output += "\n";
        for (Staff person: staff) {;
            output += person.toString() + "\n";
        }
        output += "\n";
        for (Assignment ass: assignments) {
            output += ass.toString() + "\n";
        }
        writer.write(output);
        writer.close();
    }

    // ===============================================
    // Assignment
    // ===============================================
    private void assign(int staffId, int requirementId) {
        Staff staff = getSpecificStaff(staffId);
        Requirement requirement = getRequirement(requirementId);
        assignments.add(new Assignment(staff, requirement));
    }

    // ===============================================
    // Getters and Setters
    // ===============================================
    // Requirements
    public Requirement getRequirement(int id) {
        for (Requirement item: requirements) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

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
    public Staff getSpecificStaff(int id) {
        for (Staff person: staff) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

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
