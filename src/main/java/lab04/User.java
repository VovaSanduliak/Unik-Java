package lab04;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a user who can own multiple houses.
 * Each user has a unique identifier, username, email, password, and a list of houses they own.
 */
public class User implements Comparable<User>{
    /**
     * Unique identifier for the user.
     */
    private UUID id;

    /**
     * The username of the user.
     */
    private String userName;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * List of houses owned by the user.
     */
    private List<House> houses;

    /**
     * Constructs a new User with the specified username, email, and password.
     * Initializes the list of houses to an empty list.
     *
     * @param userName the username of the user
     * @param email the email address of the user
     * @param password the password of the user
     */
    @JsonCreator
    public User(@JsonProperty("userName") String userName, @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.houses = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName the new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param newEmail the new email address
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the user.
     *
     * @param newPassword the new password
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Returns the list of houses owned by the user.
     *
     * @return the list of houses
     */
    public List<House> getHouses() {
        return this.houses;
    }

    /**
     * Adds a house to the list of houses owned by the user.
     *
     * @param house the house to add
     */
    public void addHouse(House house) {
        this.houses.add(house);
    }

    /**
     * Adds many houses owned by the user.
     *
     * @param houses list of houses to add
     */
    public void addHouses(List<House> houses) {
        houses.forEach(house -> addHouse(house));
    }

    /**
     * Removes a house from the list of houses owned by the user.
     *
     * @param house the house to remove
     * @return true if the house was successfully removed, false otherwise
     */
    public Boolean removeHouse(House house) {
        return this.houses.remove(house);
    }

    /**
     * Returns a string representation of the User, including its id, username, and email.
     *
     * @return a string representation of the User
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", email=" + email + "}";
    }

    /**
     * Compares this User to another object. Two User instances are considered equal if their IDs match.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    /**
     * Returns a hash code value for the User, based on its ID, username, and email.
     *
     * @return a hash code value for the User
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email);
    }

    @Override
    public int compareTo(User u) {
        return this.getUserName().compareTo(u.getUserName());
    }
}
