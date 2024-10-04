package lab03.Comparators;

import lab03.House;

import java.util.Comparator;

public class HouseComparator {
    public static Comparator<House> byDeviceCount() {
        return Comparator.comparing(house -> house.getDevices().size());
    }
}
