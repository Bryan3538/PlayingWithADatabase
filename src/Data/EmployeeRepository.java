package Data;

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

    //Create
    public boolean saveEmployee(Employee emp) throws SQLException {
        DataSource ds = DataSourceFactory.getMySqlDataSource();
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO employees(ID, FirstName, LastName, DepartmentNumber, LocationID) VALUES (?, ?, ?, ?, ?)";
        int affected = 0;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, emp.getID());
            stmt.setString(2, emp.getFirstName());
            stmt.setString(3, emp.getLastName());
            stmt.setInt(4, emp.getDept());
            stmt.setInt(5, emp.getLocation());

            affected = stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return (affected >= 1);
    }

    //Read
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

    //Update
    public int updateEmployee(Employee emp) throws SQLException {
        Employee existing = findEmployeeByID(emp.getID());
        DataSource ds = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        int affected = 0;

        if (existing != null) {
            try {
                ds = DataSourceFactory.getMySqlDataSource();
                conn = ds.getConnection();

                stmt = conn.prepareStatement("UPDATE employees SET FirstName=?, LastName=?, DepartmentNumber=?, LocationID=? WHERE ID=?");
                stmt.setString(1, emp.getFirstName());
                stmt.setString(2, emp.getLastName());
                stmt.setInt(3, emp.getDept());
                stmt.setInt(4, emp.getLocation());
                stmt.setInt(5, emp.getID());

                affected = stmt.executeUpdate();
            } finally {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        }

        return affected;
    }

    //Delete
    public int deleteEmployee(int id) throws SQLException {
        DataSource ds = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        String sql = "DELETE FROM employees WHERE ID=?";
        int affected = 0;

        try {
            ds = DataSourceFactory.getMySqlDataSource();
            conn = ds.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            affected = stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return affected;
    }
}
