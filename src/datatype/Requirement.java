package datatype;

import java.util.ArrayList;
import java.util.Scanner;

import datatype.builder.RequirementBuilder;

public class Requirement {
    private int id;
    private int courseId;
    private int numberOfStaffNeeded;
    private ArrayList<String> trainingsNeeded;
   
    public Requirement(int id, int courseId, int numberOfStaffNeeded, ArrayList<String> trainingsNeeded) {
        this.id = id;
        this.courseId = courseId;
        this.numberOfStaffNeeded = numberOfStaffNeeded;
        this.trainingsNeeded = trainingsNeeded;
    }

    public String toString() {
        String output = id + "," + courseId + "," + numberOfStaffNeeded + ",";
        for (String item: trainingsNeeded) {
            output += item + " ";
        }
        return output;
    }
    
    /**
    This is the descriptive string for debugging.
    */ 
    public String debugString() {    
        String output = "";
        output += "(" + id + " for "+ courseId +"): ";
        output +=  numberOfStaffNeeded + " people needed, trainings: ";
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

            int id = scanner.nextInt();
            int courseId = scanner.nextInt();
            int numberOfStaffNeeded = scanner.nextInt();

            ArrayList<String> allRequirements = new ArrayList<String>();
            if (scanner.hasNext()) {
                Scanner s = new Scanner(scanner.next());
                while (s.hasNext()) {
                    allRequirements.add(s.next());
                }
            }
            
            RequirementBuilder builder = RequirementBuilder.getInstance();

            Requirement temp = builder.setId(id)
                                       .setCourseId(courseId)
                                       .setNumberOfStaffNeeded(numberOfStaffNeeded)
                                       .setTrainingsNeeded(allRequirements)
                                       .build();

            requirements.add(temp);
        }
    }
    
    // ===============================================
    // Getters
    // ===============================================
    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getNumberOfStaffNeeded() {
        return numberOfStaffNeeded;
    }

    public ArrayList<String> getTrainingsNeeded() {
        return trainingsNeeded;
    }
}
