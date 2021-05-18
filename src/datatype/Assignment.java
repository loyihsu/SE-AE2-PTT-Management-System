package src.datatype;

public class Assignment {
    private Staff staff;
    private Requirement requirement;

    public Assignment(Staff staff, Requirement requirement) {
        this.staff = staff;
        this.requirement = requirement;
    }

    public String toString() {
        return staff.getId() + "," + requirement.getId();
    }

    public String debugString() {
        return staff.getName() + " is assigned to " + requirement.getId();
    }

    public Staff getStaff() {
        return staff;
    }

    public Requirement getRequirement() {
        return requirement;
    }
}