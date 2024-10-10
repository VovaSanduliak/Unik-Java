package lab04;

/**
 * Represents a heating device. This class extends the {@link Device} class and
 * adds functionality specific to heating devices, such as temperature control.
 */
public class HeatDevice extends Device {
    /**
     * The temperature setting of the heating device.
     */
    private int temperature;

    /**
     * Constructs a new HeatDevice with the given name and a default temperature of 20°C.
     *
     * @param name the name of the heating device
     */
    public HeatDevice() {
        this.type = DeviceType.HEAT_DEVICE;
        this.temperature = 20;
    }

    /**
     * Sets the temperature of the heating device.
     *
     * @param temperature the new temperature to set
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * Returns the current temperature of the heating device.
     *
     * @return the current temperature
     */
    public int getTemperature() {
        return this.temperature;
    }

    /**
     * Returns a string representation of the HeatDevice, including the device's ID, name, model,
     * type, and house address (if assigned).
     *
     * @return a string representation of the HeatDevice
     */
    @Override
    public String toString() {
        return "HeatDevice {" +
                "id=" + getId() +
                ", name=" + getName() +
                ", model=" + getModel() +
                ", type=" + getType() +
                ", house=" + (getHouseAddress() != null ? getHouseAddress() : "no house assigned") +
                "}";
    }

    /**
     * Compares this HeatDevice to another object. Two devices are considered equal if they
     * have the same ID.
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