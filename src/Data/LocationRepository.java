package Data;

import Models.Location;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Bryan on 11/28/2015.
 */
public class LocationRepository {
    public Location findLocationById(int locationID) throws SQLException {
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

        } finally {
            if (results != null) results.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return loc;
    }
}
