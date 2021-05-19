package src.database.interfaces.tables;

import java.util.ArrayList;

public interface Table<T> {
    public ArrayList<T> getTable();

    public void add(T item);

    public void remove(T item);
}
