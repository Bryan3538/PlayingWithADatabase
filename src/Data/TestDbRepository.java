package Data;

import Models.Department;
import Models.Employee;
import Models.Location;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 11/21/2015.
 */
public class TestDbRepository {
    //I know this should be separate for different tables and such, but I'm being lazy for testing purposes :)

    //Employee stuff
    public List<Employee> getAllEmployees() {
        List<Employee> emps = new ArrayList<>();
        Employee emp;
        Location loc;
        DataSource ds = DataSourceFactory.getMySqlDataSource();

        Connection conn = null;
        Statement stmt = null;
        ResultSet results = null;

        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            results = stmt.executeQuery("SELECT ID, FirstName, LastName, DepartmentNumber, LocationID FROM employees");

            while (results.next()) {
                emp = new Employee();

                emp.setID(results.getInt("ID"));
                emp.setFirstName(results.getString("FirstName"));
                emp.setLastName(results.getString("LastName"));
                emp.setDept(results.getInt("DepartmentNumber"));
                emp.setLocation(results.getInt("LocationID"));
                emps.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (results != null) results.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return emps;
    }

    public Employee findEmployeeByID(int employeeID) {
        Employee emp = null;
        DataSource ds = DataSourceFactory.getMySqlDataSource();
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "SELECT FirstName, LastName, DepartmentNumber, LocationID FROM employees WHERE id=?";
        ResultSet results = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeID);
            results = stmt.executeQuery();

            if(results.next()) {
                emp = new Employee();
                emp.setID(employeeID);
                emp.setFirstName(results.getString("firstname"));
                emp.setLastName(results.getString("lastname"));
                emp.setDept(results.getInt("departmentnumber"));
                emp.setLocation(results.getInt("locationid"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return emp;
    }

    //Department stuff
    public Department findDepartmentById(int departmentNumber) {
        Department dept = null;
        DataSource ds = DataSourceFactory.getMySqlDataSource();
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "SELECT DepartmentNumber, DepartmentName, SupervisorID FROM departments WHERE DepartmentNumber=?";
        ResultSet results = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, departmentNumber);
            results = stmt.executeQuery();

            if(results.next()) {
                dept = new Department();
                dept.setDepartmentNumber(departmentNumber);
                dept.setDepartmentName(results.getString("DepartmentName"));
                dept.setSupervisor(results.getInt("supervisorid"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return dept;
    }


    //Location stuff
    public Location findLocationById(int locationID) {
        Location loc = null;
        DataSource ds = DataSourceFactory.getMySqlDataSource();
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "SELECT LocationID, LocationName, StreetAddress, Zip FROM locations WHERE LocationID=?";
        ResultSet results = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, locationID);
            results = stmt.executeQuery();

            if (results.next()) { //it should only return 1 thing!
                loc = new Location();
                loc.setLocationId(results.getInt("LocationID"));
                loc.setLocationName(results.getString("LocationName"));
                loc.setStreetAddress(results.getString("StreetAddress"));
                loc.setZip(results.getString("Zip"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (results != null) results.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loc;
    }
}
