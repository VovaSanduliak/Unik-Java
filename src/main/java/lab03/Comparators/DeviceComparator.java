package lab03.Comparators;

import lab03.Device;

import java.util.Comparator;

public class DeviceComparator {
    public static Comparator<Device> byName() {
        return Comparator.comparing(Device::getName);
    }

    public static Comparator<Device> byActiveStatus() {
        return Comparator
                .comparing(Device::isActive)
                .thenComparing(Device::getName);
    }

    public static Comparator<Device> byCreatedDate() {
        return Comparator.comparing(Device::getCreatedAt);
    }

    public static Comparator<Device> byType() {
        return Comparator
                .comparing(Device::getType)
                .thenComparing(Device::getName);
    }
}
