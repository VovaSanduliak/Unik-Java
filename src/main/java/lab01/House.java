package lab01;

import java.util.List;
import java.util.UUID;

public class House {
    private final UUID id;
    private String name;
    private String address;
    private List<Device> devices;

    public House(String name, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    public void addDevice(Device device) {
        this.devices.add(device);
    }

    // TODO: toString
    @Override
    public String toString() {
        return "";
    }

    // TODO: equals
    // TODO: hashCode
}


