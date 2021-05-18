package src.datatype;

import java.util.ArrayList;

public class Requirement {
    private int id;
    private int courseId;
    private int numberOfStaffNeeded;
    private String startDate;
    private String endDate;
    private ArrayList<String> trainingsNeeded;
   
    public Requirement(int id, int courseId, int numberOfStaffNeeded, String startDate, String endDate, ArrayList<String> trainingsNeeded) {
        this.id = id;
        this.courseId = courseId;
        this.numberOfStaffNeeded = numberOfStaffNeeded;
        this.trainingsNeeded = trainingsNeeded;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        String output = id + "," + courseId + "," + numberOfStaffNeeded + "," + startDate+ "," + endDate + ",";
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
        output += "(" + id + " for "+ courseId +") ("+ startDate + "->" + endDate +"): ";
        output +=  numberOfStaffNeeded + " people needed, trainings: ";
        for (String training: trainingsNeeded) {
            output += training + ", ";
        }
        return output;
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public ArrayList<String> getTrainingsNeeded() {
        return trainingsNeeded;
    }

    public boolean equals(Object another) {
        if (another instanceof Requirement) {
            return this.toString().equals(another.toString());
        }
        return false;
    }
}
