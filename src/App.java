import Data.DepartmentRepository;
import Data.EmployeeRepository;
import Data.LocationRepository;
import Models.Department;
import Models.Employee;
import Models.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Created by Bryan on 11/21/2015.
 */
public class App {

    public static void main(String[] args) throws SQLException {
        EmployeeRepository empRepo = new EmployeeRepository();
        LocationRepository locRepo = new LocationRepository();
        DepartmentRepository deptRepo = new DepartmentRepository();
        long start = System.currentTimeMillis();
        List<Employee> emps = empRepo.getAllEmployees();
        long end = System.currentTimeMillis();
        System.out.println(String.format("Query took %d ms.", end-start));

        for(Employee emp : emps) {
            Location loc = locRepo.findLocationById(emp.getLocation());
            Department dept = deptRepo.findDepartmentById(emp.getDept());

            System.out.println(String.format("Emp ID:\t\t%d", emp.getID()));
            System.out.println(String.format("First Name:\t%s", emp.getFirstName()));
            System.out.println(String.format("Last Name:\t%s", emp.getLastName()));
            System.out.println(String.format("Department:\t%s", dept.getDepartmentName()));
            System.out.println(String.format("Location:\t%s", loc.getLocationName()));
            System.out.println("--------------------------------");
        }

        Employee emp = empRepo.findEmployeeByID(1);
        System.out.println(emp);
        System.out.println("--------------------------------");
        emp = empRepo.findEmployeeByID(-1);
        System.out.println(emp);
        System.out.println("--------------------------------");
        //INSERT STATEMENT
        System.out.println("INSERT");
        Employee newEmp = new Employee();
        newEmp.setID(100);
        newEmp.setFirstName("I'm New!");
        newEmp.setLastName("Newbie");
        newEmp.setDept(1);
        newEmp.setLocation(1);

        try {
            if(empRepo.saveEmployee(newEmp)) {
                System.out.println("Employee successfully inserted!");
                Employee someEmp = empRepo.findEmployeeByID(100);
                System.out.println(someEmp);
            } else
                System.out.println("Your employee could not be inserted!");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }


        System.out.println("--------------------------------");
        System.out.println("UPDATE");
        newEmp.setFirstName(generateName());
        newEmp.setLastName(generateName());
        int affected = empRepo.updateEmployee(newEmp);

        if(affected >= 0) {
            System.out.println("Something was updated!");
            Employee someEmp = empRepo.findEmployeeByID(newEmp.getID());
            System.out.println(someEmp);
        } else {
            System.out.println("Nothing was updated!");
        }


        System.out.println("--------------------------------");
        System.out.println("DELETE");

        if(empRepo.deleteEmployee(newEmp.getID()) >= 0) {
            System.out.println("Employee was successfully deleted!");
            newEmp = empRepo.findEmployeeByID(newEmp.getID());
            System.out.println(newEmp);
        } else
            System.out.println("Employee was not deleted");


    }

    private static String generateName() {
        String[] names = new String[] {"John", "Jim", "Jack", "Jane", "Jessica", "Jasmine"};
        return names[new Random().nextInt(names.length)];
    }
}
