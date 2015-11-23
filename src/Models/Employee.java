package Models;

/**
 * Created by Bryan on 11/21/2015.
 */
public class Employee {
    private int ID;
    private String firstName;
    private String lastName;
    private int dept;
    private int loc;


    public Employee() {
        ID = 0;
        firstName = "";
        lastName = "";
        dept = -1;
        loc = -1;
    }

    public Employee(int ID, String firstName, String lastName, int dept, int loc) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dept = dept;
        this.loc = loc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int getLocation() {
        return loc;
    }

    public void setLocation(int loc) {
        this.loc = loc;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("ID:\t%d\n", ID));
        builder.append(String.format("First Name:\t%s\n", firstName));
        builder.append(String.format("Last Name:\t%s\n", lastName));
        builder.append(String.format("Department:\t%s\n", dept));
        builder.append(String.format("Location:\t%s", loc));

        return builder.toString();
    }
}
