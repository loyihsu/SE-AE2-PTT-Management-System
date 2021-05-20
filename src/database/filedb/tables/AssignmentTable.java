package src.database.filedb.tables;

import src.database.interfaces.tables.TableFilterableByAssignmentElements;
import src.database.interfaces.tables.TableFindable;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.database.types.interfaces.AssignmentElement;

import java.util.ArrayList;
import java.util.Scanner;

public class AssignmentTable implements TableFilterableByAssignmentElements<Assignment> {
    private ArrayList<Assignment> table;

    public AssignmentTable() {
        table = new ArrayList<Assignment>();
    }

    public AssignmentTable(ArrayList<String> raw, TableFindable<Requirement> requirements, TableFindable<Staff> staff) {
        table = new ArrayList<Assignment>();

        Scanner scanner;
        for (String line : raw) {
            scanner = new Scanner(line).useDelimiter(",");
            int sid = scanner.nextInt();
            int rid = scanner.nextInt();
            table.add(new Assignment(staff.find(sid), requirements.find(rid)));
        }
    }

    public String toString() {
        String output = "";
        for (Assignment item : table) {
            output += item.toString() + "\n";
        }
        return output;
    }

    // ===============================================
    // Table
    // ===============================================
    public ArrayList<Assignment> getTable() {
        return table;
    }

    public void add(Assignment item) {
        boolean flag = true;
        for (Assignment existingItem : table) {
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

    // ===============================================
    // TableFilterableByAssignmentElements
    // ===============================================
    public ArrayList<Assignment> getAllItemsRelatedTo(AssignmentElement element) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();
        for (Assignment item : table) {
            if (item.getStaff().equals(element) || item.getRequirement().equals(element)) {
                temp.add(item);
            }
        }
        return temp;
    }

    public void cleanAllItemsRelatedTo(AssignmentElement element) {
        ArrayList<Assignment> temp = new ArrayList<Assignment>();
        for (Assignment assignment : table) {
            if (!assignment.getStaff().equals(element) && !assignment.getRequirement().equals(element)) {
                temp.add(assignment);
            }
        }
        table = temp;
    }
}
