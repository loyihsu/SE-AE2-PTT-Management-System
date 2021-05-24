package src.database.interfaces.tables;

import java.util.ArrayList;

public interface Table<T> {
    ArrayList<T> getTable();

    void add(T item);

    void remove(T item);
}
