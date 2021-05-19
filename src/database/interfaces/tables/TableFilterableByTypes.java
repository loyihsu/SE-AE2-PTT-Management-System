package src.database.interfaces.tables;

import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;

import java.util.ArrayList;

public interface TableFilterableByTypes<T> extends Table<T> {
    void cleanAllItemsRelatedTo(Staff staff);

    void cleanAllItemsRelatedTo(Requirement requirement);

    void cleanAllItemsRelatedTo(Assignment assignment);

    ArrayList<Assignment> getAllItemsRelatedTo(Staff staff);

    ArrayList<Assignment> getAllItemsRelatedTo(Requirement requirement);

    ArrayList<Assignment> getAllItemsRelatedTo(Assignment assignment);
}
