package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Bryan on 11/20/2015.
 */
public class MyConnection {
    private Connection conn;
    private Properties connectionProps;

    public MyConnection() {
        conn = null;
        connectionProps = null;
    }

    public Connection openConnection(String serverName, String port, String user, String password) {
        connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + serverName + ":" + port + "/",
                    connectionProps
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void closeConnection() {
        if(conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Connection getConnection() {
        return conn;
    }
}
