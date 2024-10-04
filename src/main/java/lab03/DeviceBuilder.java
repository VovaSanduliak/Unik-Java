package lab03;

/**
 * A generic builder class for creating and configuring Device objects.
 * @param <T> The type of device being built, which extends Device.
 */
public class DeviceBuilder<T extends Device> {
    private String name;
    private String model;
    private House house;
    private DeviceType type;
    private boolean isActive;
    private int warrantyDurationInMonths;

    /**
     * Sets the name of the device.
     * @param name The name to set for the device.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the model of the device.
     * @param model The model to set for the device.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setModel(String model) {
        this.model = model;
        return this;
    }

    public DeviceBuilder<T> setWarranty(int warrantyDurationInMonths) {
        this.warrantyDurationInMonths = warrantyDurationInMonths;
        return this;
    }

    /**
     * Assigns a house to the device.
     * @param house The house to assign to the device.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setHouse(House house) {
        this.house = house;
        return this;
    }

    /**
     * Sets the active state of the device.
     * @param isActive True to make the device active, false otherwise.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     * Builds and configures the device with the set properties.
     * @param device The device instance to be built and configured.
     * @return The configured device.
     */
    public T build(T device) {
        device.setName(this.name);
        device.setModel(this.model);
        device.setWarrantyDuration(this.warrantyDurationInMonths);
        device.setHouse(this.house);
        device.turnOn();

        return device;
    }
}
