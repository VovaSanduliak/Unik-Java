package lab03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a house that can have multiple devices assigned to it.
 * Each house has a unique ID, a name, an address, and a list of devices.
 */
public class House {
    /**
     * The unique identifier of the house.
     */
    private final UUID id;

    /**
     * The name of the house.
     */
    private String name;

    /**
     * The address of the house.
     */
    private String address;

    /**
     * The list of devices associated with the house.
     */
    private final List<Device> devices;

    /**
     * Constructs a new House with the given name and address.
     *
     * @param name    the name of the house
     * @param address the address of the house
     */
    public House(String name, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.devices = new ArrayList<>();
    }

    /**
     * Returns the unique ID of the house.
     *
     * @return the house's UUID
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Returns the name of the house.
     *
     * @return the name of the house
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the house.
     *
     * @param name the new name of the house; must not be null or empty
     * @throws IllegalArgumentException if the name is null or an empty string
     */
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name cannot be null");
        if (name.isEmpty()) throw new IllegalArgumentException("name cannot be an empty string");

        this.name = name;
    }

    /**
     * Returns the address of the house.
     *
     * @return the address of the house
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address of the house.
     *
     * @param address the new address of the house
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the list of devices assigned to the house.
     *
     * @return the list of devices in the house
     */
    public List<Device> getDevices() {
        return this.devices;
    }

    /**
     * Adds a device to the house's list of devices.
     *
     * @param device the device to add
     */
    public void addDevice(Device device) {
        this.devices.add(device);
    }

    /**
     * Adds many devices to the house's list of devices.
     *
     * @param devices the device to add
     */
    public void addDevices(List<Device> devices) {
        devices.forEach(device -> addDevice(device));
    }
    
    /**
     * Removes a device from the house's list of devices.
     *
     * @param device the device to remove
     * @return true if the device was successfully removed, false if it was not in the list
     */
    public Boolean removeDevice(Device device) {
        return devices.remove(device);
    }

    /**
     * Returns a string representation of the House, including its ID, name, and address.
     *
     * @return a string representation of the House
     */
    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                "}";
    }

    /**
     * Compares this house with another object. Two houses are considered equal if they
     * have the same unique ID.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id.equals(house.id);
    }

    /**
     * Returns the hash code of the house, based on its ID, name, and address.
     *
     * @return the hash code of the house
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
