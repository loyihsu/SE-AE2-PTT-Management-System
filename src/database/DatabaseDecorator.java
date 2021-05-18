package src.database;

import src.datatype.*;

import java.util.ArrayList;

public class DatabaseDecorator {
    Database database;

    public DatabaseDecorator(Database database) {
        this.database = database;
    }

    public void cleanlyRemoveStaff(Staff person) {
        database.getStaffTable().remove(person);

        ArrayList<Assignment> temp = new ArrayList<Assignment>();

        for (Assignment assignment: database.getAssignmentTable().getTable()) {
            if (assignment.getStaff().equals(person)) {
                temp.add(assignment);
            }
        }

        for (Assignment item: temp) {
            database.getAssignmentTable().remove(item);
        }
    }

    public void cleanlyRemoveRequirement(Requirement requirement) {
        database.getRequirementTable().remove(requirement);

        ArrayList<Assignment> temp = new ArrayList<Assignment>();

        for (Assignment assignment: database.getAssignmentTable().getTable()) {
            if (assignment.getRequirement().equals(requirement)) {
                temp.add(assignment);
            }
        }

        for (Assignment item: temp) {
            database.getAssignmentTable().remove(item);
        }
    }
}
