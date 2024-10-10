package lab04.Comparators;

import lab04.House;

import java.util.Comparator;

public class HouseComparator {
    public static Comparator<House> byDeviceCount() {
        return Comparator.comparing(house -> house.getDevices().size());
    }
}
