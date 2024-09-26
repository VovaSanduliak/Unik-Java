package lab02.Services;

import lab02.Device;

import java.util.List;
import java.util.Optional;

public class DeviceService {
    private Device device;

    public DeviceService(Device device) {
        this.device = device;
    }

    public static Optional<Device> findByName(List<Device> devices, String name) {
        return devices.stream()
                .filter(device -> device.getName().equals(name))
                .findFirst();
    }
}
