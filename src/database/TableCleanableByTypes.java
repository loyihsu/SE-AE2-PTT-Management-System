package src.database;

import src.database.types.*;

public interface TableCleanableByTypes<T> extends Table<T> {
    void cleanAllItemsRelatedTo(Staff staff);
    void cleanAllItemsRelatedTo(Requirement requirement);
    void cleanAllItemsRelatedTo(Assignment assignment);
}
