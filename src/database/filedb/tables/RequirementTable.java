package src.database.filedb.tables;

import src.database.interfaces.tables.TableFindable;
import src.database.types.Requirement;
import src.database.types.builder.RequirementBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class RequirementTable implements TableFindable<Requirement> {
    private final ArrayList<Requirement> table;

    public RequirementTable() {
        table = new ArrayList<Requirement>();
    }

    public RequirementTable(ArrayList<String> raw) {
        table = new ArrayList<Requirement>();
        Scanner scanner;
        for (String item : raw) {
            scanner = new Scanner(item).useDelimiter(",");

            int id = scanner.nextInt();
            int courseId = scanner.nextInt();
            int numberOfStaffNeeded = scanner.nextInt();
            String sDate = scanner.next();
            String eDate = scanner.next();

            ArrayList<String> allRequirements = new ArrayList<String>();
            if (scanner.hasNext()) {
                Scanner s = new Scanner(scanner.next());
                while (s.hasNext()) {
                    allRequirements.add(s.next());
                }
            }

            RequirementBuilder builder = RequirementBuilder.getInstance();

            Requirement temp = builder
                    .setId(id)
                    .setCourseId(courseId)
                    .setNumberOfStaffNeeded(numberOfStaffNeeded)
                    .setTrainingsNeeded(allRequirements)
                    .setStartDate(sDate)
                    .setEndDate(eDate)
                    .build();

            table.add(temp);
        }
    }

    public String toString() {
        String output = "";
        for (Requirement item : table) {
            output += item.toString() + "\n";
        }
        return output;
    }

    // ===============================================
    // Table
    // ===============================================
    public ArrayList<Requirement> getTable() {
        return table;
    }

    public void add(Requirement requirement) {
        table.add(requirement);
    }

    public void remove(Requirement requirement) {
        table.remove(requirement);
    }

    // ===============================================
    // TableFindable
    // ===============================================
    public Requirement find(int id) {
        for (Requirement item : table) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Requirement> findWithSkills(ArrayList<String> skills) {
        ArrayList<Requirement> temp = table;
        for (String skill : skills) {
            ArrayList<Requirement> newTemp = new ArrayList<Requirement>();
            for (Requirement s : temp) {
                if (s.getTrainingsNeeded().contains(skill)) {
                    newTemp.add(s);
                }
            }
            temp = newTemp;
        }
        return temp;
    }
}
