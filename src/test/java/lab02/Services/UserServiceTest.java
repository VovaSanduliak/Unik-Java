package lab02.Services;

import lab02.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private Device heatDevice1;
    private Device heatDevice2;
    private Device heatDevice3;

    private House house1;
    private House house2;

    private User user;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        this.heatDevice1 = new LightDevice("Some heat device");
        this.heatDevice2 = new HeatDevice("Another heat device");
        this.heatDevice3 = new HeatDevice("Another heat device");

        this.house1 = new House("Hen house", "Ne golovna, 1");
        this.house2 = new House("Garage", "Ne golovna, 1");

        house1.addDevices(List.of(heatDevice1, heatDevice2));
        house2.addDevice(heatDevice3);

        this.user = new User("Barack Obama", "elpresidente@mail.com", "1234");
        this.user.addHouses(List.of(house1, house2));

        this.userService = new UserService(user);
    }

    @Test
    void getTotalDeviceCount() {
        int totalDeviceCount = userService.getTotalDeviceCount();

        assertEquals(3, totalDeviceCount, "Device count must be 3");
    }

    @Test
    void getAllDevices() {
        List<Device> allDevices = userService.getAllDevices();

        assertAll(() -> {
            assertEquals(3, allDevices.size(), "Device count must be 3");
            assertTrue(allDevices.stream().anyMatch(device -> device.getName().equals("Some heat device")));
            assertTrue(allDevices.stream().anyMatch(device -> device.getName().equals("Another heat device")));
            assertTrue(allDevices.stream().anyMatch(device -> device.getName().equals("Another heat device")));
        });
    }

    @Test
    void testGetDevicesGroupedByHouse() {
        Map<String, List<Device>> devicesGroupedByHouse = userService.getDevicesGroupedByHouse();

        assertAll(() -> {
            assertEquals(2, devicesGroupedByHouse.size(), "There must be 2 houses");
            assertTrue(devicesGroupedByHouse.containsKey("Hen house"));
            assertTrue(devicesGroupedByHouse.containsKey("Garage"));
            assertEquals(2, devicesGroupedByHouse.get("Hen house").size(), "House1 must contain 2 devices");
            assertEquals(1, devicesGroupedByHouse.get("Garage").size(), "House1 must contain 1 device");
        });
    }
}