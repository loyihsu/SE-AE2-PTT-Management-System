package datatype;

import java.util.ArrayList;
import java.util.Scanner;

import datatype.builder.StaffBuilder;

public class Staff {
    private int id;
    private String name;
    private String dateOfJoining;
    private ArrayList<String> trainingsReceived;
    
    public Staff(int id, String name, String dateOfJoining, ArrayList<String> trainingsReceived) {
        this.id = id;
        this.name = name;
        this.dateOfJoining = dateOfJoining;
        this.trainingsReceived = trainingsReceived;
    }

    public String toString() {
        String output = id + "," + name + "," + dateOfJoining + ",";
        for (String item: trainingsReceived) {
            output += item + " ";
        }
        return output;
    }

    /**
    This is the descriptive string for debugging.
    */ 
    public String debugString() {
        String output = "";
        output += "[" + id + "," + name + " (" + dateOfJoining + ")]: ";
        
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

            staff.add(temp);
        }
    }

    // ===============================================
    // Getter and Setter
    // ===============================================
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public ArrayList<String> getTrainingsReceived() {
        return trainingsReceived;
    }
}