package src.database.filedb.tables;

import java.util.ArrayList;
import java.util.Scanner;

import src.database.*;
import src.datatype.*;
import src.datatype.builder.*;

public class StaffTable implements TableFindable<Staff> {
    private ArrayList<Staff> table;
    
    public StaffTable() {
        table = new ArrayList<Staff>();
    }

    public StaffTable(ArrayList<String> raw) {
        table = new ArrayList<Staff>();
        Scanner scanner;
        for (String item: raw) {
            scanner = new Scanner(item).useDelimiter(",");

            int id = scanner.nextInt();
            String name = scanner.next();
            String date = scanner.next();

            ArrayList<String> allTrainings = new ArrayList<String>();
            if (scanner.hasNext()) {
                Scanner s = new Scanner(scanner.next());
                while (s.hasNext()) {
                    allTrainings.add(s.next());
                }
            }
            StaffBuilder builder = StaffBuilder.getInstance();

            Staff temp = builder.setId(id)
                                    .setName(name)
                                    .setDateOfJoining(date)
                                    .setTrainingsReceived(allTrainings)
                                    .build();

            table.add(temp);
        }
    }

    public Staff find(int id) {
        for (Staff item: table) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Staff> findWithSkills(ArrayList<String> skills) {
        ArrayList<Staff> temp = table;
        for (String skill: skills) {
            ArrayList<Staff> newTemp = new ArrayList<Staff>();
            for (Staff s: temp) {
                if (s.getTrainingsReceived().contains(skill)) {
                    newTemp.add(s);
                }
            }
            temp = newTemp;
        }
        return temp;
    }

    public ArrayList<Staff> getTable() {
        return table;
    }

    public void add(Staff staff) {
        table.add(staff);
    }

    public void remove(Staff staff) {
        table.remove(staff);
    }

    public String toString() {
        String output = "";
        for (Staff item: table) {
            output += item.toString() + "\n";
        }
        return output;
    }
}
