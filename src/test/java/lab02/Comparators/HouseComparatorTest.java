package lab02.Comparators;

import lab02.HeatDevice;
import lab02.House;
import lab02.LightDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseComparatorTest {
    private House house1;
    private House house2;

    @BeforeEach
    void setUp() {
        house1 = new House("Hen house", "Ne golovna, 1");
        house2 = new House("Garage", "Ne golovna, 1");
    }

    @Test
    void byDeviceCount_shouldReturnOne() {
        house1.addDevice(new LightDevice("Light"));
        house1.addDevice(new LightDevice("Automatic open door"));

        house2.addDevice(new HeatDevice("Heater"));

        assertEquals(1, HouseComparator.byDeviceCount().compare(house1, house2));
    }

    @Test
    void byDeviceCount_shouldReturnEquals() {
        house1.addDevice(new LightDevice("Light"));
        house1.addDevice(new HeatDevice("Heater"));

        house2.addDevice(new HeatDevice("Heater"));
        house2.addDevice(new LightDevice("Light"));

        assertEquals(0, HouseComparator.byDeviceCount().compare(house1, house2));
    }
}