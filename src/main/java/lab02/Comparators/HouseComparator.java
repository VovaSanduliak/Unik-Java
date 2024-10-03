package lab02.Comparators;

import lab02.House;

import java.util.Comparator;

/**
 * The {@code HouseComparator} class provides a comparator for comparing {@link House} objects
 * based on the number of devices they contain.
 */
public class HouseComparator {
    /**
     * Returns a comparator that compares {@link House} objects by the number of devices in each house.
     * The comparison is done based on the size of the devices list.
     *
     * @return a comparator that compares {@link House} objects by the number of devices
     */
    public static Comparator<House> byDeviceCount() {
        return Comparator.comparing(house -> house.getDevices().size());
    }
}
