package lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceBuilderTest {
    @Test
    public void testBuildHeatDevice() {
        DeviceBuilder<HeatDevice> builder = new DeviceBuilder<>();
        HeatDevice device = builder
                .setName("HeatDevice")
                .setModel("HD-2024")
                .setHouse(new House("My House", "123 Main St"))
                .setActive(true)
                .build(new HeatDevice("HeatDevice"));

        assertAll(() -> {
            assertEquals("HeatDevice", device.getName());
            assertEquals("HD-2024", device.getModel());
            assertEquals(DeviceType.HEAT_DEVICE, device.getType());
            assertTrue(device.isActive());
        });
    }

    @Test
    public void testBuildLightDevice() {
        DeviceBuilder<LightDevice> builder = new DeviceBuilder<>();
        LightDevice device = builder
                .setName("LightDevice")
                .setModel("LD-2024")
                .setHouse(new House("My House", "123 Main St"))
                .setActive(true)
                .build(new LightDevice("LightDevice"));

        assertAll(() -> {
            assertEquals("LightDevice", device.getName());
            assertEquals("LD-2024", device.getModel());
            assertEquals(DeviceType.LIGHT_DEVICE, device.getType());
            assertTrue(device.isActive());
        });
    }
}
