package src.database.types;

public class Assignment {
    private final Staff staff;
    private final Requirement requirement;

    public Assignment(Staff staff, Requirement requirement) {
        this.staff = staff;
        this.requirement = requirement;
    }

    public boolean equals(Object another) {
        if (another instanceof Assignment) {
            return (toString().equals(another.toString()));
        }
        return false;
    }

    public String toString() {
        return staff.getId() + "," + requirement.getId();
    }

    // ===============================================
    // Getters
    // ===============================================
    public Staff getStaff() {
        return staff;
    }

    public Requirement getRequirement() {
        return requirement;
    }
}
