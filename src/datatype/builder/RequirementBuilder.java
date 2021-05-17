package datatype.builder;

import java.util.ArrayList;

import datatype.Requirement;

public class RequirementBuilder extends Builder<Requirement> {
    private int id = 0;
    private int courseId = 0;
    private int numberOfStaffNeeded = 0;
    private ArrayList<String> trainingsNeeded = new ArrayList<String>();
    
    public static RequirementBuilder instance = new RequirementBuilder();

    private RequirementBuilder() { }

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

    public static RequirementBuilder getInstance() {
        return instance;
    }

    public Requirement build() {
        return new Requirement(id,courseId,numberOfStaffNeeded,trainingsNeeded);
    }
}