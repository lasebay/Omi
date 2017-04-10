package a404_notfound.sourceappwater.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lase on 2/12/2017.
 *
 * A registered user in the model.
 */

public class RegisteredUser {
    // make username final?
    private String username;
    private String address;
    private String coordinates;
    private int loginAttempts;

    /**
     * Generic constructor
     */
    RegisteredUser() {
        username = "No Name";
        coordinates = "None";
        address = "None";
        loginAttempts = 0;
    }

    /**
     * Constructor for making an actual account
     *
     * @param name username of account user
     */
    public RegisteredUser(String name) {
        this();
        username = name;
    }

    /**
     * Getter for the username of the account
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of the accounts
     *
     * @param username the new username
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * Getter for coordinates of user
     *
     * @return the longitude and latitude of the user
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Setter for coordinates of user
     *
     * @param coordinates the new location of the user
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Getter for the number of login attempts
     *
     * @return the number of failed login attempts for the user
     */
    public int getLoginAttempts() {
        return loginAttempts;
    }

    /**
     * Setter for login attempts
     *
     * @param loginAttempts the new number of login attempts
     */
    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    /**
     * Getter for the current address of the user
     *
     * @return the address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for the address of the user
     *
     * @param address the new address of the user
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User";
    }

    /**
     * Gives a map of all the user's account's information
     *
     * @return a Map with all the user's information
     */
    public Map<String, Object> toMap() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("name", username);
        ret.put("addrs", address);
        ret.put("coord", coordinates);
        ret.put("logAtmps", loginAttempts);

        return ret;
    }
}
