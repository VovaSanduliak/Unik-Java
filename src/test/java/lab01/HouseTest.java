package lab01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {
    private House house;
    private final String houseName = "Hen house";
    private final String houseAddress = "Ne golovna, 1";
    private Device device;

    @BeforeEach
    public void beforeEach() {
        this.house = new House(houseName, houseAddress);
        this.device = new HeatDevice("Heater");
    }

    @Test
    public void testHouseCreation() {
        assertAll(() -> {
            assertNotNull(house.getId());
            assertEquals(houseName, house.getName());
            assertEquals(houseAddress, house.getAddress());
        });
    }

    @Test
    public void testSetName() {

        house.setName("Meat warehouse");
        assertEquals("Meat warehouse", house.getName());

        assertThrows(IllegalArgumentException.class, () -> house.setName(null));
        assertThrows(IllegalArgumentException.class, () -> house.setName(""));
    }

    @Test
    public void testAddRemoveDevice() {
        house.addDevice(device);
        assertTrue(house.getDevices().contains(device));

        house.removeDevice(device);
        assertFalse(house.getDevices().contains(device));
    }

    @Test
    public void testHouseToString() {
        House house = new House("Hen house", "Ne golovna, 1");

        String expected =
                "House{" +
                "id=" + house.getId() +
                ", name=Hen house" +
                ", address=Ne golovna, 1" +
                "}";

        assertEquals(expected, house.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        House house2 = new House(houseName, houseAddress);

        assertAll(() -> {
            assertNotEquals(house, house2);
            assertNotEquals(house.hashCode(), house2.hashCode());
        });
    }

    @Test
    public void testEqualsWithSameClass() {
        House house2 = new House("Meat warehouse", "Ne golovna, 1");

        assertNotEquals(house, house2);
    }

    @Test
    public void testEqualsWithNull() {
        assertNotEquals(null, house);

    }

    @Test
    public void testEqualsWithEqualsIds() {
        UUID sameId = UUID.randomUUID();

        House house1 = new House("House1", "123 Main St") {
            @Override
            public UUID getId() {
                return sameId;
            }
        };

        House house2 = new House("House2", "456 Other St") {
            @Override
            public UUID getId() {
                return sameId;
            }
        };

        assertNotEquals(house1, house2);
    }
}
