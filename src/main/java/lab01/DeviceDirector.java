package lab01;

/**
 * A class responsible for constructing specific device configurations.
 * Uses the {@link DeviceBuilder} to configure and build {@link HeatDevice} and {@link LightDevice}.
 */
public class DeviceDirector {
    private final DeviceBuilder<HeatDevice> heatDeviceBuilder;
    private final DeviceBuilder<LightDevice> lightDeviceBuilder;

    /**
     * Initializes the DeviceDirector with builders for heat and light devices.
     * The builders allow for flexible configuration of each device type.
     */
    public DeviceDirector() {
        this.heatDeviceBuilder = new DeviceBuilder<>();
        this.lightDeviceBuilder = new DeviceBuilder<>();
    }

    /**
     * Builds a specific HeatDevice with predefined configurations.
     * The device has the name "Handy Heat NFJ-03" and model "NFJ-03".
     *
     * @return a fully constructed {@link HeatDevice} with pre-set properties.
     */
    public HeatDevice buildHeatDeviceNFJ03() {
        return (HeatDevice) heatDeviceBuilder
                .setName("Handy Heat NFJ-03")
                .setModel("NFJ-03")
                .build(new HeatDevice("Handy Heat NFJ-03"));
    }

    /**
     * Builds a specific LightDevice with predefined configurations.
     * The device has the name "Yaviii Light WS-1816" and model "WS-1816".
     *
     * @return a fully constructed {@link LightDevice} with pre-set properties.
     */
    public LightDevice buildBasicLightDevice() {
        return (LightDevice) lightDeviceBuilder
                .setName("Yaviii Light WS-1816")
                .setModel("WS-1816")
                .build(new LightDevice("Yaviii Light WS-1816"));
    }
}
