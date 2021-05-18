package src;

import src.database.*;
import src.datatype.*;

import java.util.ArrayList;

public class DatabaseHandler {
    TableFindable<Staff> staff;
    TableFindable<Requirement> requirements;
    Table<Assignment> assignments;

    public DatabaseHandler(TableFindable<Staff> staff, TableFindable<Requirement> requirements, Table<Assignment> assignments) {
        this.staff = staff;
        this.requirements = requirements;
        this.assignments = assignments;
    }

    public void cleanlyRemoveStaff(Staff person) {
        staff.remove(person);

        ArrayList<Assignment> temp = new ArrayList<Assignment>();

        for (Assignment assignment: assignments.getTable()) {
            if (assignment.getStaff().equals(person)) {
                temp.add(assignment);
            }
        }

        for (Assignment item: temp) {
            assignments.remove(item);
        }
    }

    public void cleanlyRemoveRequirement(Requirement requirement) {
        requirements.remove(requirement);

        ArrayList<Assignment> temp = new ArrayList<Assignment>();

        for (Assignment assignment: assignments.getTable()) {
            if (assignment.getRequirement().equals(requirement)) {
                temp.add(assignment);
            }
        }

        for (Assignment item: temp) {
            assignments.remove(item);
        }
    }

}
