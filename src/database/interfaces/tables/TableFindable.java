package src.database.interfaces.tables;

import java.util.ArrayList;

public interface TableFindable<T> extends Table<T> {
    public T find(int id);

    public ArrayList<T> findWithSkills(ArrayList<String> skills);
}
