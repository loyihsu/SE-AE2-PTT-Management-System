package src.database.types;

public class Assignment {
    private Staff staff;
    private Requirement requirement;

    public Assignment(Staff staff, Requirement requirement) {
        this.staff = staff;
        this.requirement = requirement;
    }

    public boolean equals(Object another) {
        if (another instanceof Assignment) {
            return (toString().equals(toString()));
        }
        return false;
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