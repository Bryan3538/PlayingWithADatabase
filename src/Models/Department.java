package Models;

/**
 * Created by Bryan on 11/21/2015.
 */
public class Department {
    private int departmentNumber;
    private String departmentName;
    private int supervisor;

    public Department() {
        departmentNumber = -1;
        departmentName = "Unknown";
        supervisor = -1;
    }

    public Department(int departmentNumber, String departmentName, int supervisor) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.supervisor = supervisor;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }
}
