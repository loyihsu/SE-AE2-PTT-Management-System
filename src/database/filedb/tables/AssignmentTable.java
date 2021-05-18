package src.database.filedb.tables;

import java.util.ArrayList;
import java.util.Scanner;

import src.database.*;
import src.datatype.*;

public class AssignmentTable implements Table<Assignment> {
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
        table.add(item);
    }

    public void remove(Assignment item) {
        table.remove(item);
    }

    public String toString() {
        String output = "";
        for (Assignment item: table) {
            output += item.toString() + "\n";
        }
        return output;
    }
}