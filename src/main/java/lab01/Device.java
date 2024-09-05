package lab01;

import java.util.UUID;

public class Device {
    private final UUID id;
    private String name;

    public Device(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
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

    // TODO: toString
    // TODO: equals
    // TODO: hashCode
}
