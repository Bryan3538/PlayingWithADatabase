package Data;

import Models.Department;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Bryan on 11/28/2015.
 */
public class DepartmentRepository {
    public Department findDepartmentById(int departmentNumber) throws SQLException {
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

            if (results.next()) {
                dept = new Department();
                dept.setDepartmentNumber(departmentNumber);
                dept.setDepartmentName(results.getString("DepartmentName"));
                dept.setSupervisor(results.getInt("supervisorid"));
            }

        } finally {
            if (results != null) results.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }


        return dept;
    }
}
