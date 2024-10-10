package lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceBuilderTest {

    @Test
    void build() {
        DeviceBuilder<HeatDevice> builder = new DeviceBuilder<>();
        HeatDevice device = builder
                .setName("Heat device")
                .setModel("HD-2024")
                .setSerialNumber("HD-123456")
                .setHouse(new House("My House", "123 Main St"))
                .setWarranty(3)
                .setActive(true)
                .build(new HeatDevice());

        assertAll(() -> {
            assertEquals("Heat device", device.getName());
            assertEquals("HD-2024", device.getModel());
            assertEquals(DeviceType.HEAT_DEVICE, device.getType());
            assertEquals(3, device.getWarrantyDuration());
            assertTrue(device.isActive());
        });
    }

    @Test
    void buildThrowsIllegalArgumentException() {
        DeviceBuilder<HeatDevice> builder = new DeviceBuilder<>();

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> builder
                        .setName(null)
                        .setModel("HD-2024-very-mega-useful-and-necessary-powerful-device")
//                        .setSerialNumber("HD-123456")
                        .setHouse(new House("My House", "123 Main St"))
                        .setWarranty(1)
                        .setActive(false)
                        .build(new HeatDevice())
        );

        assertTrue(thrown.getMessage().contains("Warranty cannot be less than 3 months"));
        assertTrue(thrown.getMessage().contains("Name cannot be null"));
        assertTrue(thrown.getMessage().contains("Model name is too long, maximum 20 characters is required"));
        assertTrue(thrown.getMessage().contains("Warranty cannot be less than 3 months"));


    }
}