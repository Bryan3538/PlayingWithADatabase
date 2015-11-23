package Models;

/**
 * Created by Bryan on 11/21/2015.
 */
public class Location {
    private int locationId;
    private String locationName;
    private String streetAddress;
    private String zip;

    public Location() {
        locationId = 0;
        locationName = "Unknown";
        streetAddress = "";
        zip = "";
    }

    public Location(int locationId, String locationName, String streetAddress, String zip) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.streetAddress = streetAddress;
        this.zip = zip;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
