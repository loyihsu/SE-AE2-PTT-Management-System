package src.database.types.builder;

import src.database.types.Staff;

import java.util.ArrayList;

public class StaffBuilder extends Builder<Staff> {
    private int id;
    private String name;
    private String dateOfJoining;
    private ArrayList<String> trainingsReceived;

    // ===============================================
    // Singleton
    // ===============================================
    public static StaffBuilder instance = new StaffBuilder();

    private StaffBuilder() {
    }

    /**
     * Get the instance of builder.
     * Fields (id, name, dateOfJoining, trainingsReceived)
     *
     * @return The singleton builder.
     */
    public static StaffBuilder getInstance() {
        return instance;
    }

    // ===============================================
    // Builder
    // ===============================================
    public Staff build() {
        return new Staff(id, name, dateOfJoining, trainingsReceived);
    }

    // ===============================================
    // Setters
    // ===============================================
    public StaffBuilder setId(int value) {
        id = value;
        return this;
    }

    public StaffBuilder setName(String value) {
        name = value;
        return this;
    }

    public StaffBuilder setDateOfJoining(String value) {
        dateOfJoining = value;
        return this;
    }

    public StaffBuilder setTrainingsReceived(ArrayList<String> value) {
        trainingsReceived = value;
        return this;
    }
}
