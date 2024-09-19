package lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest
{
    @Test
    public void testDeviceCreation() {
        Device device = new HeatDevice("Heater");

        assertAll(() -> {
            assertNotNull(device.getId());
            assertEquals("Heater", device.getName());
        });
    }

    @Test
    public void testTurnOnAndOff() {
        Device device = new HeatDevice("Heater");

        assertFalse(device.isActive());
        device.turnOn();
        assertTrue(device.isActive());
        device.turnOff();
        assertFalse(device.isActive());
    }

    @Test
    public void testGetHouseAddress() {
        House house = new House("Hen house", "Ne golovna, 1");
        Device device = new HeatDevice("Heater");
        device.setHouse(house);

        assertEquals("Ne golovna, 1", device.getHouseAddress());

        Device deviceWithoutHouse = new LightDevice("Light");
        assertEquals("no house assigned", deviceWithoutHouse.getHouseAddress());

        house.setAddress("Golovna, 2");
        assertEquals("Golovna, 2", device.getHouseAddress());


    }

    @Test
    public void testDeviceTurnOnAndTurnOff() {
        Device device = new HeatDevice("Heater");

        assertAll(() -> {
            assertTrue(device.turnOff());
            assertTrue(device.turnOn());
            assertTrue(device.turnOff());
        });
    }

    @Test
    public void testEqualsAndHashCode() {
        Device device = new HeatDevice("Heater");
        Device device2 = new HeatDevice("Heater");

        assertAll(() -> {
            assertNotEquals(device, device2);
            assertNotEquals(device.hashCode(), device2.hashCode());
        });
    }
}
