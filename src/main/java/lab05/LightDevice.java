package lab05;

/**
 * Represents a LightDevice that extends the abstract Device class.
 * It includes additional properties specific to light devices such as brightness.
 */
public class LightDevice extends Device {
    /**
     * The brightness level of the light device, ranging from 0 to 100.
     */
    private int brightness;

    /**
     * Constructs a new LightDevice with the specified name.
     * Initializes the brightness to 50 by default.
     *
     * @param name the name of the light device
     */
    public LightDevice() {
        this.type = DeviceType.LIGHT_DEVICE;
        this.setBrightness(50);
    }

    /**
     * Sets the brightness level of the light device.
     *
     * @param brightness the new brightness level (must be between 0 and 100)
     * @throws IllegalArgumentException if the brightness is outside the 0-100 range
     */
    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) throw new IllegalArgumentException("Brightness must be from 0 to 100");
        this.brightness = brightness;
    }

    /**
     * Returns the current brightness level of the light device.
     *
     * @return the brightness level (0-100)
     */
    public int getBrightness() {
        return this.brightness;
    }

    /**
     * Returns a string representation of the LightDevice, including its id, name, model, type, and house address.
     *
     * @return a string representation of the LightDevice
     */
    @Override
    public String toString() {
        return "LightDevice {" +
                "id=" + getId() +
                ", name=" + getName() +
                ", model=" + getModel() +
                ", type=" + getType() +
                ", house=" + (getHouseAddress() != null ? getHouseAddress() : "no house assigned") +
                "}";
    }

    /**
     * Compares this LightDevice to another object. Two LightDevice instances are considered equal if their IDs match.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;

        return this.getId().equals(device.getId());
    }
}
