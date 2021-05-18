package src.datatype;

import java.util.ArrayList;

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

    public void addTrainingReceived(String training) {
        if (!trainingsReceived.contains(training)) {
            trainingsReceived.add(training);
        }
    }

    public void editName(String newName) {
        name = newName;
    }

    public boolean equals(Object another) {
        if (another instanceof Staff) {
            return this.toString().equals(another.toString());
        }
        return false;
    }
}