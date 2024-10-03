package lab02.Comparators;

import lab02.Device;

import java.util.Comparator;

/**
 * The {@code DeviceComparator} class provides several static methods for comparing
 * {@link Device} objects based on different fields such as name, active status,
 * creation date, and type.
 */
public class DeviceComparator {
    /**
     * Returns a comparator that compares {@link Device} objects by their name.
     *
     * @return a comparator that compares {@link Device} objects by name
     */
    public static Comparator<Device> byName() {
        return Comparator.comparing(Device::getName);
    }

    /**
     * Returns a comparator that compares {@link Device} objects by their active status.
     * If two devices have the same active status, they are compared by name.
     *
     * @return a comparator that compares {@link Device} objects by active status
     *         and then by name if the active statuses are the same
     */
    public static Comparator<Device> byActiveStatus() {
        return Comparator
                .comparing(Device::isActive)
                .thenComparing(Device::getName);
    }

    /**
     * Returns a comparator that compares {@link Device} objects by their creation date.
     *
     * @return a comparator that compares {@link Device} objects by creation date
     */
    public static Comparator<Device> byCreatedDate() {
        return Comparator.comparing(Device::getCreatedAt);
    }

    /**
     * Returns a comparator that compares {@link Device} objects by their type.
     * If two devices have the same type, they are compared by name.
     *
     * @return a comparator that compares {@link Device} objects by type
     *         and then by name if the types are the same
     */
    public static Comparator<Device> byType() {
        return Comparator
                .comparing(Device::getType)
                .thenComparing(Device::getName);
    }
}
