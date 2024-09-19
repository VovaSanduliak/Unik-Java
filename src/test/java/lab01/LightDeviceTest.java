package lab01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LightDeviceTest {
    @Test
    public void testLightDeviceCreation() {
        LightDevice device = new LightDevice("Light");
        assertNotNull(device.getId());
        assertEquals("Light", device.getName());
        assertEquals(DeviceType.LIGHT_DEVICE, device.getType());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 50, 100})
    public void testSetBrightness(int value) {
        LightDevice device = new LightDevice("Light");
        device.setBrightness(value);
        assertEquals(value, device.getBrightness());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 105})
    public void testSetBrightnessThrowError(int value) {
        LightDevice device = new LightDevice("Light");
        assertThrows(IllegalArgumentException.class,  () -> device.setBrightness(value));
    }

    @Test
    public  void testHeatDeviceToString() {
        House house = new House("Hen house", "Ne golovna, 1");
        LightDevice lightDevice = new LightDevice("Light");
        lightDevice.setModel("Model-Y");
        lightDevice.setHouse(house);

        String expected = "LightDevice {" +
                "id=" + lightDevice.getId() +
                ", name=Light" +
                ", model=Model-Y" +
                ", type=LIGHT_DEVICE" +
                ", house=Ne golovna, 1" +
                "}";

        assertEquals(expected, lightDevice.toString());

        house.setAddress(null);
        expected = "LightDevice {" +
                "id=" + lightDevice.getId() +
                ", name=Light" +
                ", model=Model-Y" +
                ", type=LIGHT_DEVICE" +
                ", house=no house assigned" +
                "}";

        assertEquals(expected, lightDevice.toString());
    }

    @Test
    public void testEqualsWithSameObject() {
        LightDevice device = new LightDevice("Light");

        assertTrue(device.equals(device));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        LightDevice device = new LightDevice("Light");
        String differentObject = "Some string";

        assertFalse(device.equals(differentObject));
    }

    @Test
    public void testEqualsWithNull() {
        LightDevice device = new LightDevice("Light");

        assertFalse(device.equals(null));
    }


}
