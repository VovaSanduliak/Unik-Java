package lab02.Services;

import lab02.Device;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;


public class DeviceService {
    private final List<Device> devices;

    public DeviceService(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> findByName(List<Device> devices, String name) {
        return devices.stream()
                .filter(device -> device.getName().equals(name))
                .collect(Collectors.toList());
    }
}
