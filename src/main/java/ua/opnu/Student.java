package ua.opnu;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String group;
    private final int[] marks;

    public Student(String firstName, String lastName, String group, int[] marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.marks = marks;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    public boolean hasDebts() {
        for (int mark : marks) {
            if (mark < 60) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + group + ")";
    }
}
