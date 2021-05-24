package src.database.interfaces.tables;

import java.util.ArrayList;

public interface TableFindable<T> extends Table<T> {
    T find(int id);

    ArrayList<T> findWithSkills(ArrayList<String> skills);
}
