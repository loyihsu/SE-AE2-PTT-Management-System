package src.database.types.builder;

import src.database.types.Requirement;

import java.util.ArrayList;

public class RequirementBuilder extends Builder<Requirement> {
    private int id = 0;
    private int courseId = 0;
    private int numberOfStaffNeeded = 0;
    private String startDate = "";
    private String endDate = "";
    private ArrayList<String> trainingsNeeded = new ArrayList<String>();

    // ===============================================
    // Singleton
    // ===============================================
    public static RequirementBuilder instance = new RequirementBuilder();

    private RequirementBuilder() {
    }

    public static RequirementBuilder getInstance() {
        return instance;
    }

    // ===============================================
    // Builder
    // ===============================================
    public Requirement build() {
        return new Requirement(id, courseId, numberOfStaffNeeded, startDate, endDate, trainingsNeeded);
    }

    // ===============================================
    // Setters
    // ===============================================
    public RequirementBuilder setId(int value) {
        id = value;
        return this;
    }

    public RequirementBuilder setCourseId(int value) {
        courseId = value;
        return this;
    }

    public RequirementBuilder setNumberOfStaffNeeded(int value) {
        numberOfStaffNeeded = value;
        return this;
    }

    public RequirementBuilder setTrainingsNeeded(ArrayList<String> list) {
        trainingsNeeded = list;
        return this;
    }

    public RequirementBuilder setStartDate(String date) {
        startDate = date;
        return this;
    }

    public RequirementBuilder setEndDate(String date) {
        endDate = date;
        return this;
    }
}
