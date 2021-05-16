package datatype;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff {
    private String id;
    private String name;
    private ArrayList<String> trainingsReceived;
    
    public Staff(String employeeId, String employeeName, ArrayList<String> trainings) {
        id = employeeId;
        name = employeeName;
        trainingsReceived = trainings;
    }

    public String toString() {
        // This is for debugging.
        String output = "";
        output += "[" + id + "," + name + "]: ";
        
        for (String training: trainingsReceived) {
            output += training + ", ";
        }

        return output;
    }
    
    // ===============================================
    // Helpers
    // ===============================================
    /**
    This method takes the staff in the file (in Strings list), parse the contents
    and create Staff objects to store them into the list. 
    The file.txt file structure and the data structure should be updated simultaneously.
    @param arr The requirements Strings list.
    @param staff The output ArrayList object for generated items to be added to.
     */
    public static void parse(ArrayList<String> arr, ArrayList<Staff> staff) {
        Scanner scanner;
        for (String item: arr) {
            scanner = new Scanner(item).useDelimiter(",");

            String id = scanner.next();
            String name = scanner.next();

            ArrayList<String> allTrainings = new ArrayList<String>();
            if (scanner.hasNext()) {
                Scanner s = new Scanner(scanner.next());
                while (s.hasNext()) {
                    allTrainings.add(s.next());
                }
            }
            Staff ts = new Staff(id, name, allTrainings);
            staff.add(ts);
        }
    }

    // ===============================================
    // Getter and Setter
    // ===============================================
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTrainingsReceived() {
        return trainingsReceived;
    }
}