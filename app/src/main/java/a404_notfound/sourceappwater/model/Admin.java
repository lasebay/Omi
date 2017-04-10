package a404_notfound.sourceappwater.model;

/**
 * Created by Joshua on 2/21/2017.
 */

public class Admin {
    private String username;
    private String address;
    private String coordinates;
    private int loginAttempts;

    /**
     * Generic constructor
     */
    private Admin() {
        username = "No Name";
        coordinates = "None";
        address = "None";
        loginAttempts = 0;
    }

    /**
     * Constructor for admin
     *
     * @param name username of Admin
     */
    public Admin(String name) {
        this();
        username = name;
    }

    /**
     * Getter for the username
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username
     * TODO: remove this
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the admin's coordinates
     *
     * @return a String with the admin's latitude and longitude
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Setter for the admin's coordinates
     * Used when admin changes location
     *
     * @param coordinates the new location of the admin
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Getter for login attempts
     *
     * @return the number of consecutive failed login attempts
     */
    public int getloginAttempts() {
        return loginAttempts;
    }

    /**
     * Setter for login attempts
     *
     * @param loginAttempts the updated amount of failed login attempts
     */
    public void setloginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    /**
     * Setter for the street address of the admin
     *
     * @param address the new current address of the admin
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for the admin's address
     *
     * @return the current street address of the admin
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return String representation of the account type
     */
    public String toString() {
        return "Admin";
    }
}
