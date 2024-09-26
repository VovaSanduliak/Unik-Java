package lab02.Services;

import lab02.House;
import  lab02.User;
import  lab02.Device;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {
    private final User user;

    public UserService(User user) {
        this.user = user;
    }

    public int getTotalDeviceCount() {
        return user.getHouses().stream()
                .mapToInt(house -> house.getDevices().size())
                .sum();
    }

    public List<Device> getAllDevices() {
        return user.getHouses()
                .stream()
                .flatMap(house -> house.getDevices()
                        .stream())
                        .collect(Collectors.toList());
    }

    public Map<String, List<Device>> getDevicesGroupedByHouse() {
        return user.getHouses().stream()
                .collect(Collectors.toMap(
                        House::getName,
                        House::getDevices
                ));
    }
}
