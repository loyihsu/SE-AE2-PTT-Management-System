package src.database.types;

import src.database.types.interfaces.AssignmentElement;

import java.util.ArrayList;

public class Staff implements AssignmentElement {
    private final int id;
    private final String name;
    private final String dateOfJoining;
    private final ArrayList<String> trainingsReceived;

    public Staff(int id, String name, String dateOfJoining, ArrayList<String> trainingsReceived) {
        this.id = id;
        this.name = name;
        this.dateOfJoining = dateOfJoining;
        this.trainingsReceived = trainingsReceived;
    }

    public boolean equals(Object another) {
        if (another instanceof Staff) {
            return this.toString().equals(another.toString());
        }
        return false;
    }

    public String toString() {
        String output = id + "," + name + "," + dateOfJoining + ",";
        for (String item : trainingsReceived) {
            output += item + " ";
        }
        return output;
    }

    // ===============================================
    // Assignment Element
    // ===============================================
    public String getDisplayString() {
        return "(" + id + ", " + name + ")";
    }

    // ===============================================
    // Getter and Helpers
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

    public void addTrainingReceived(String training) {
        if (!trainingsReceived.contains(training)) {
            trainingsReceived.add(training);
        }
    }
}
