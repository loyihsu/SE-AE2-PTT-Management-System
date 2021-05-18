package src.database;

import src.datatype.*;

public interface TableCleanableByTypes<T> extends Table<T> {
    void cleanAllInstancesRelatedTo(Staff staff);
    void cleanAllInstancesRelatedTo(Requirement requirement);
    void cleanAllInstancesRelatedTo(Assignment assignment);
}
