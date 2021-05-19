package src.database;

import src.datatype.*;

public class DatabaseDecorator {
    private Database database;

    public DatabaseDecorator(Database database) {
        this.database = database;
    }

    // ===============================================
    // Cleaner methods
    // ===============================================
    public void cleanlyRemoveStaff(Staff staff) {
        database.getStaffTable().remove(staff);
        database.getAssignmentTable().cleanAllInstancesRelatedTo(staff);
    }

    public void cleanlyRemoveRequirement(Requirement requirement) {
        database.getRequirementTable().remove(requirement);
        database.getAssignmentTable().cleanAllInstancesRelatedTo(requirement);
    }
    // ===============================================
    // Getter
    // ===============================================
    public Database getDatabase() {
        return database;
    }
}
