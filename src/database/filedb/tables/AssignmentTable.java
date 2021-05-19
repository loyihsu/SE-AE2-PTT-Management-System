package src.database.filedb.tables;

import java.util.ArrayList;
import java.util.Scanner;

import src.database.*;
import src.database.types.*;

public class AssignmentTable implements TableFilterableByTypes<Assignment> {
    private ArrayList<Assignment> table;

    public AssignmentTable() {
        table = new ArrayList<Assignment>();
    }

    public AssignmentTable(ArrayList<String> raw, TableFindable<Requirement> requirements, TableFindable<Staff> staff) {
        table = new ArrayList<Assignment>();

        Scanner scanner;
        for (String line: raw) {
            scanner = new Scanner(line).useDelimiter(",");
            int sid = scanner.nextInt();
            int rid = scanner.nextInt();
            table.add(new Assignment(staff.find(sid), requirements.find(rid)));
        }   
    }

    public ArrayList<Assignment> getTable() {
        return table;
    }

    public void add(Assignment item) {
        boolean flag = true;
        for (Assignment existingItem: table) {
            if (existingItem.equals(item)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            table.add(item);
        }
    }

    public void remove(Assignment item) {
        table.remove(item);
    }

    public ArrayList<Assignment> getAllItemsRelatedTo(Staff staff) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();
        for (Assignment item: table) {
            if (item.getStaff().equals(staff)) {
                temp.add(item);
            }
        }
        return temp;
    }

    public ArrayList<Assignment> getAllItemsRelatedTo(Requirement requirement) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();
        for (Assignment item: table) {
            if (item.getRequirement().equals(requirement)) {
                temp.add(item);
            }
        }
        return temp;
    }

    public ArrayList<Assignment> getAllItemsRelatedTo(Assignment assignment) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();
        temp.add(assignment);
        return temp;
    }


    public void cleanAllItemsRelatedTo(Staff staff) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();

        for (Assignment assignment: table) {
            if (!assignment.getStaff().equals(staff)) {
                temp.add(assignment);
            }
        }

        table = temp;
    }

    public void cleanAllItemsRelatedTo(Assignment assignment) {
        this.remove(assignment);
    }

    public void cleanAllItemsRelatedTo(Requirement requirement) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();

        for (Assignment assignment: table) {
            if (!assignment.getRequirement().equals(requirement)) {
                temp.add(assignment);
            }
        }

        table = temp;
    }

    public String toString() {
        String output = "";
        for (Assignment item: table) {
            output += item.toString() + "\n";
        }
        return output;
    }
}
