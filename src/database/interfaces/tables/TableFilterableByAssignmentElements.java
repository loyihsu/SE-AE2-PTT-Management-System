package src.database.interfaces.tables;

import src.database.types.Assignment;
import src.database.types.interfaces.AssignmentElement;

import java.util.ArrayList;

public interface TableFilterableByAssignmentElements<T> extends Table<T> {
    void cleanAllItemsRelatedTo(AssignmentElement element);

    ArrayList<Assignment> getAllItemsRelatedTo(AssignmentElement element);
}
