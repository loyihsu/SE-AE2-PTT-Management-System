package src.database.filedb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import src.database.*;
import src.datatype.*;
import src.database.filedb.tables.*;

public class FileDB implements Database {
    private String filepath;
    
    private TableFindable<Requirement> requirements = new RequirementTable();
    private TableFindable<Staff> staff = new StaffTable();
    private Table<Assignment> assignments = new AssignmentTable();

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
        int section = 0;
        
        while (scanner.hasNextLine()) {
            temp = scanner.nextLine();
            if (temp.equals("")) {
                section++;
                continue;
            }
            if (section == 0) {
                reqs.add(temp);
            } else if (section == 1) {
                people.add(temp);
            } else {
                ass.add(temp);
            }
        }
        
        requirements = new RequirementTable(reqs);
        staff = new StaffTable(people);
        assignments = new AssignmentTable(ass, requirements, staff);

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
        output += requirements.toString() + "\n";
        output += staff.toString() + "\n";
        output += assignments.toString();
        writer.write(output);
        writer.close();        
    }

    public Table<Assignment> getAssignmentTable() {
        return assignments;
    }

    public TableFindable<Requirement> getRequirementTable() {
        return requirements;
    }

    public TableFindable<Staff> getStaffTable() {
        return staff;
    }
}
