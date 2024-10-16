package lab05.Models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

/**
 * A generic builder class for creating and configuring Device objects.
 *
 * @param <T> The type of device being built, which extends Device.
 */
public class DeviceBuilder<T extends Device> {
    @NotNull(message = "Name cannot be null")
    @Length(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Length(min = 2, message = "Model name is too short, minimum 2 characters is required")
    @Length(max = 20, message = "Model name is too long, maximum 20 characters is required")
    private String model;

    @NotNull(message = "serialNumber cannot be null")
    @NotEmpty(message = "serialNumber cannot be empty string")
    private String serialNumber;

    @NotNull(message = "House cannot be null")
    private House house;

//    @NotNull(message = "Device type cannot be null")
//    private DeviceType type;

    @AssertTrue
    private boolean isActive;

    @Min(value = 3, message = "Warranty cannot be less than 3 months")
    @Max(value = 60, message = "Warranty cannot be more than 60 months")
    private int warrantyDurationInMonths;

    /**
     * Sets the name of the device.
     *
     * @param name The name to set for the device.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the model of the device.
     *
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
     *
     */
    public DeviceBuilder<T> setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    /**
     * Assigns a house to the device.
     *
     * @param house The house to assign to the device.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setHouse(House house) {
        this.house = house;
        return this;
    }

    /**
     * Sets the active state of the device.
     *
     * @param isActive True to make the device active, false otherwise.
     * @return The current instance of DeviceBuilder for method chaining.
     */
    public DeviceBuilder<T> setActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     * Builds and configures the device with the set properties.
     *
     * @param device The device instance to be built and configured.
     * @return The configured device.
     */
    public T build(T device) {
        ValidatorFactory factory = buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        device.setName(this.name);
        device.setModel(this.model);
        device.setWarrantyDuration(this.warrantyDurationInMonths);
        device.setHouse(this.house);
        device.turnOn();

        Set<ConstraintViolation<DeviceBuilder<T>>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<DeviceBuilder<T>> violation : violations) {
                sb
                        .append("\nField: ")
                        .append(violation.getPropertyPath())
                        .append("\nInvalid value: ")
                        .append(violation.getInvalidValue())
                        .append("\nProblem: ")
                        .append(violation.getMessage())
                        .append("\n");
            }
            throw new IllegalArgumentException(sb.toString());
        }

        return device;
    }
}