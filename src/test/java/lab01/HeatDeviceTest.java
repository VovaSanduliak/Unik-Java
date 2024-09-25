package lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeatDeviceTest {
    @Test
    public void testHeatDeviceCreation() {
        HeatDevice device = new HeatDevice("Heater");

        assertAll(() -> {
            assertNotNull(device.getId());
            assertEquals("Heater", device.getName());
            assertEquals(DeviceType.HEAT_DEVICE, device.getType());
        });
    }

    @Test
    public void testSetTemperature() {
        HeatDevice device = new HeatDevice("Heater");
        device.setTemperature(25);

        assertEquals(25, device.getTemperature());
    }

    @Test
    public void testHeatDeviceToString() {
        House house = new House("Hen house", "Ne golovna, 1");
        HeatDevice heatDevice = new HeatDevice("Heater");
        heatDevice.setModel("Model-X");
        heatDevice.setHouse(house);

        String expected = "HeatDevice {" +
                "id=" + heatDevice.getId() +
                ", name=Heater" +
                ", model=Model-X" +
                ", type=HEAT_DEVICE" +
                ", house=Ne golovna, 1" +
                "}";

        assertEquals(expected, heatDevice.toString());
    }
}