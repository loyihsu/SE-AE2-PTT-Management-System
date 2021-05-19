package src.database;

import src.datatype.*;

public interface TableCleanableByTypes<T> extends Table<T> {
    void cleanAllItemsRelatedTo(Staff staff);
    void cleanAllItemsRelatedTo(Requirement requirement);
    void cleanAllItemsRelatedTo(Assignment assignment);
}
