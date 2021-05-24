package src.database.types;

import src.database.types.interfaces.AssignmentElement;

import java.util.ArrayList;

public class Requirement implements AssignmentElement {
    private final int id;
    private final int courseId;
    private final int numberOfStaffNeeded;
    private final String startDate;
    private final String endDate;
    private final ArrayList<String> trainingsNeeded;

    public Requirement(int id, int courseId, int numberOfStaffNeeded, String startDate, String endDate, ArrayList<String> trainingsNeeded) {
        this.id = id;
        this.courseId = courseId;
        this.numberOfStaffNeeded = numberOfStaffNeeded;
        this.trainingsNeeded = trainingsNeeded;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean equals(Object another) {
        if (another instanceof Requirement) {
            return this.toString().equals(another.toString());
        }
        return false;
    }

    public String toString() {
        String output = id + "," + courseId + "," + numberOfStaffNeeded + "," + startDate + "," + endDate + ",";
        for (String item : trainingsNeeded) {
            output += item + " ";
        }
        return output;
    }

    // ===============================================
    // Assignment Element
    // ===============================================
    public String getDisplayString() {
        return "(Lab " + id + ")";
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
}
