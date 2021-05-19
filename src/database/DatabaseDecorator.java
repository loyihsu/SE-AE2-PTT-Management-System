package src.database;

import src.database.interfaces.tables.Database;
import src.database.types.Requirement;
import src.database.types.Staff;

import java.util.ArrayList;

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
        database.getAssignmentTable().cleanAllItemsRelatedTo(staff);
    }

    public void cleanlyRemoveRequirement(Requirement requirement) {
        database.getRequirementTable().remove(requirement);
        database.getAssignmentTable().cleanAllItemsRelatedTo(requirement);
    }

    // ===============================================
    // Getter
    // ===============================================
    public Database getDatabase() {
        return database;
    }

    public ArrayList<Requirement> getRequirementsWithoutEnoughPeople() {
        ArrayList<Requirement> items = new ArrayList<Requirement>();

        for (Requirement item : database.getRequirementTable().getTable()) {
            int numberNeeded = item.getNumberOfStaffNeeded();
            int numberHas = database.getAssignmentTable().getAllItemsRelatedTo(item).size();
            if (numberHas < numberNeeded) {
                items.add(item);
            }
        }
        return items;
    }
}
