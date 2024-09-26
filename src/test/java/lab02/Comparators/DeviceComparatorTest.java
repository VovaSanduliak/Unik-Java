package lab02.Comparators;

import lab02.Device;
import lab02.HeatDevice;
import lab02.LightDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceComparatorTest {
    private Device heatDevice1;
    private Device heatDevice2;
    private Device heatDevice3;
    private Device lightDevice1;

    @BeforeEach
    public void setUp() {
        this.heatDevice1 = new LightDevice("Some heat device");
        this.heatDevice2 = new HeatDevice("Another heat device");
        this.heatDevice3 = new HeatDevice("Another heat device");
        this.lightDevice1 = new LightDevice("Light device");
    }

    @Test
    void byName() {
        assertTrue(DeviceComparator.byName().compare(heatDevice1, heatDevice2) > 0);
    }

    @Test
    void byActiveStatus() {
        assertAll(() -> {
            assertTrue(DeviceComparator.byActiveStatus().compare(heatDevice1, heatDevice2) > 0);
            assertEquals(0, DeviceComparator.byActiveStatus().compare(heatDevice2, heatDevice3));
        });
    }

    @Test
    void byCreatedDate() {
        assertAll(() -> {
            assertTrue(DeviceComparator.byCreatedDate().compare(heatDevice1, heatDevice2) <= 0, "heatDevice1 should be less than heatDevice2");
            assertTrue(DeviceComparator.byCreatedDate().compare(heatDevice2, heatDevice3) <= 0, "heatDevice2 should be less than heatDevice3");
            assertTrue(DeviceComparator.byCreatedDate().compare(heatDevice1, heatDevice3) <= 0, "heatDevice1 should be less than heatDevice3");
        });
    }

    @Test
    void byType() {
        assertAll(() -> {
            assertEquals(1, DeviceComparator.byType().compare(heatDevice1, heatDevice2));
            assertEquals(0, DeviceComparator.byType().compare(heatDevice2, heatDevice3));
            assertTrue(DeviceComparator.byType().compare(heatDevice2, lightDevice1) < 0);
        });
    }
}