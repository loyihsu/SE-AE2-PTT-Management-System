package datatype;

import java.util.ArrayList;
import java.util.Scanner;

public class Requirement {
    private String course;
    private int numberOfStaffNeeded;
    private ArrayList<String> trainingsNeeded;
    
    public Requirement(String courseString, int number, ArrayList<String> trainings) {
        course = courseString;
        numberOfStaffNeeded = number;
        trainingsNeeded = trainings;
    }

    public String toString() {
        // This is for debugging.
        String output = "";
        output += course + ": " + numberOfStaffNeeded + " needed, trainings: ";
        for (String training: trainingsNeeded) {
            output += training + ", ";
        }
        return output;
    }

    // ===============================================
    // Helpers
    // ===============================================
    /**
    This method takes the requirements in the file (in String list), parse the contents
    and create Requirement objects to store them into the list.
    The file.txt file structure and the data structure should be updated simultaneously.
    @param arr The requirements Strings list.
    @param requirements The output ArrayList object for generated items to be added to.
     */
    public static void parse(ArrayList<String> arr, ArrayList<Requirement> requirements) {
        Scanner scanner;
        for (String item: arr) {
            scanner = new Scanner(item).useDelimiter(",");

            String courseName = scanner.next();
            int number = scanner.nextInt();

            ArrayList<String> allRequirements = new ArrayList<String>();
            if (scanner.hasNext()) {
                Scanner s = new Scanner(scanner.next());
                while (s.hasNext()) {
                    allRequirements.add(s.next());
                }
            }
            
            Requirement tr = new Requirement(courseName, number, allRequirements);
            requirements.add(tr);
        }
    }
    
    // ===============================================
    // Getters
    // ===============================================
    public String getCourse() {
        return course;
    }

    public int getNumberOfStaffNeeded() {
        return numberOfStaffNeeded;
    }

    public ArrayList<String> getTrainingsNeeded() {
        return trainingsNeeded;
    }
}