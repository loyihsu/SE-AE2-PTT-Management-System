package src.database.filedb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import src.database.*;
import src.database.types.*;
import src.database.filedb.tables.*;

public class FileDB implements Database {
    private String filepath;
    
    private TableFindable<Requirement> requirements = new RequirementTable();
    private TableFindable<Staff> staff = new StaffTable();
    private TableFilterableByTypes<Assignment> assignments = new AssignmentTable();

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

    public TableFilterableByTypes<Assignment> getAssignmentTable() {
        return assignments;
    }

    public TableFindable<Requirement> getRequirementTable() {
        return requirements;
    }

    public TableFindable<Staff> getStaffTable() {
        return staff;
    }

    public Object[][] getRequirementsDisplayMatrix() {
        ArrayList<Requirement> temp = requirements.getTable();
        String[][] matrix = new String[temp.size()][7];

        for (int idx = 0, length = temp.size(); idx < length; idx++) {
            matrix[idx][0] = "" + temp.get(idx).getId();
            matrix[idx][1] = "" + temp.get(idx).getCourseId();
            matrix[idx][2] = "" + temp.get(idx).getNumberOfStaffNeeded();
            matrix[idx][3] = temp.get(idx).getStartDate();
            matrix[idx][4] = temp.get(idx).getEndDate();

            String trainings = "";
            for (String item: temp.get(idx).getTrainingsNeeded()) {
                trainings += item + " ";
            }

            matrix[idx][5] = trainings;

            String assigned = "";

            for (Assignment item: assignments.getAllItemsRelatedTo(temp.get(idx))) {
                assigned += "(" + item.getStaff().getId() + ", " + item.getStaff().getName() + ") ";
            }
            matrix[idx][6] = assigned;
        }

        return matrix;
    }

    public Object[][] getStaffDisplayMatrix() {
        ArrayList<Staff> temp = staff.getTable();
        String[][] matrix = new String[temp.size()][5];

        for (int idx = 0, length = temp.size(); idx < length; idx++) {
            matrix[idx][0] = "" + temp.get(idx).getId();
            matrix[idx][1] = temp.get(idx).getName();
            matrix[idx][2] = temp.get(idx).getDateOfJoining();

            String trainings = "";
            for (String item: temp.get(idx).getTrainingsReceived()) {
                trainings += item + " ";
            }

            matrix[idx][3] = trainings;

            String responsibility = "";

            for (Assignment item: assignments.getAllItemsRelatedTo(temp.get(idx))) {
                responsibility += "(Lab " + item.getRequirement().getId()  + ") ";
            }

            matrix[idx][4] = responsibility;
        }


        return matrix;
    }
}
