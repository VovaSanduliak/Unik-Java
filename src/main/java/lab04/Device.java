package lab04;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract class representing a generic device.
 **/
public abstract class Device implements Comparable<Device> {
    protected final UUID id;
    protected String name;
    protected String model;
    protected String serialNumber;
    protected House house;
    protected DeviceType type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-hh-mm")
    protected LocalDateTime createdAt;
    protected LocalDateTime lastTurnedOnAt;
    private int warrantyDurationInMonths;

    protected boolean isActive;

    /**
     * Constructor for the Device class.
     *
     * @param name The name of the device.
     */
    public Device() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.lastTurnedOnAt = null;
    }

    /**
     * Gets the unique ID of the device.
     *
     * @return A UUID representing the unique ID of the device.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the time when the device was created
     *
     * @return the time when the device was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the name of the device.
     *
     * @return The name of the device.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the device.
     *
     * @param name The new name for the device.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the model of the device.
     *
     * @return The model of the device, or null if not set.
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Sets the model of the device.
     *
     * @param model The new model of the device.
     */
    public void setModel(String model) {
        this.model = model;
    }


    public int getWarrantyDuration() {
        return this.warrantyDurationInMonths;
    }

    /**
     * Gets the device's serial number.
     *
     * @return Serial number of the device.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the warranty duration of the device
     * @param warrantyDurationInMonths device's warranty duration
     */
    protected void setWarrantyDuration(int warrantyDurationInMonths) {
        this.warrantyDurationInMonths = warrantyDurationInMonths;
    }

    /**
     * Gets the type of the device.
     * @return The DeviceType of the device.
     */
    public DeviceType getType() {
        return this.type;
    }

    /**
     * Assigns a house to the device.
     * @param house The house to assign to the device.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Gets the house assigned to the device.
     * @return The house assigned to the device, or null if none is assigned.
     */
    public House getHouse() {
        return this.house;
    }

    /**
     * Returns the address of the house where the device is located.
     * @return The address of the assigned house, or "no house assigned" if no house is set.
     */
    public String getHouseAddress() {
        return (getHouse() != null) ? getHouse().getAddress() : "no house assigned";
    }

    /**
     * Gets the device working time since last turned on
     * @return device's working time duration
     */
    public Duration getWorkingTime() {
        if (lastTurnedOnAt == null) {
            return Duration.ZERO;
        }

        return Duration.between(lastTurnedOnAt, LocalDateTime.now());
    }

    /**
     * Checks if the device is currently active.
     * @return True if the device is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Turns the device on.
     * @return True if the operation was successful.
     */
    public boolean turnOn() {
        this.isActive = true;
        this.lastTurnedOnAt = LocalDateTime.now();
        return true;
    }

    /**
     * Turns the device off.
     * @return True if the operation was successful.
     */
    public boolean turnOff() {
        this.isActive = false;
        this.lastTurnedOnAt = null;
        return true;
    }

    /**
     * Generates the hash code for the device.
     * @return The hash code based on the device's ID, name, type, and model.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, model);
    }

    /**
     * Returns a string representation of the device.
     * Must be implemented by subclasses.
     * @return A string that represents the device.
     */
    @Override
    public abstract String toString();

    /**
     * Compares this device to another object for equality.
     * Must be implemented by subclasses.
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public abstract boolean equals(Object o);

    @Override
    public int compareTo(Device d) {
        return this.name.compareTo(d.name);
    }
}