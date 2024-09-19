package lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceDirectorTest {
    @Test
    public void testBuildHeatDeviceNFJ03() {
        DeviceDirector director = new DeviceDirector();
        HeatDevice device = director.buildHeatDeviceNFJ03();

        assertAll(() -> {
            assertNotNull(device);
            assertEquals("Handy Heat NFJ-03", device.getName());
            assertEquals("NFJ-03", device.getModel());
            assertEquals(DeviceType.HEAT_DEVICE, device.getType());
        });
    }

    @Test
    public void testBuildBasicLightDevice() {
        DeviceDirector director = new DeviceDirector();
        LightDevice device = director.buildBasicLightDevice();

        assertAll(() -> {
            assertNotNull(device);
            assertEquals("Yaviii Light WS-1816", device.getName());
            assertEquals("WS-1816", device.getModel());
            assertEquals(DeviceType.LIGHT_DEVICE, device.getType());
        });
    }
}
