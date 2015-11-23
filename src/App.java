import Data.TestDbRepository;
import Models.Department;
import Models.Employee;
import Models.Location;

import java.util.List;

/**
 * Created by Bryan on 11/21/2015.
 */
public class App {

    public static void main(String[] args) {
        TestDbRepository repo = new TestDbRepository();
        long start = System.currentTimeMillis();
        List<Employee> emps = repo.getAllEmployees();
        long end = System.currentTimeMillis();
        System.out.println(String.format("Query took %d ms.", end-start));

        for(Employee emp : emps) {
            Location loc = repo.findLocationById(emp.getLocation());
            Department dept = repo.findDepartmentById(emp.getDept());

            System.out.println(String.format("Emp ID:\t\t%d", emp.getID()));
            System.out.println(String.format("First Name:\t%s", emp.getFirstName()));
            System.out.println(String.format("Last Name:\t%s", emp.getLastName()));
            System.out.println(String.format("Department:\t%s", dept.getDepartmentName()));
            System.out.println(String.format("Location:\t%s", loc.getLocationName()));
            System.out.println("--------------------------------");
        }

        Employee emp = repo.findEmployeeByID(1);
        System.out.println(emp);
        System.out.println("--------------------------------");
        emp =repo.findEmployeeByID(-1);
        System.out.println(emp);

    }
}
