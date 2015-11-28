package Data;

import Models.Department;
import Models.Employee;
import Models.Location;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 11/21/2015.
 */
public class EmployeeRepository {
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

    public Employee findEmployeeByID(int employeeID) throws SQLException {
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

            if (results.next()) {
                emp = new Employee();
                emp.setID(employeeID);
                emp.setFirstName(results.getString("firstname"));
                emp.setLastName(results.getString("lastname"));
                emp.setDept(results.getInt("departmentnumber"));
                emp.setLocation(results.getInt("locationid"));
            }

        } finally {
            if (results != null) results.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return emp;
    }


}
