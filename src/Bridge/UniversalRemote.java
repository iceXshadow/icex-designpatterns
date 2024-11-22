package Bridge;

// interface : Implementor
interface Device {
    void turnOn();
    void turnOff();
    boolean isPoweredOn();
}

// Concrete Implementor 1
class TV implements Device {
    private boolean poweredOn;

    @Override
    public void turnOn() {
        poweredOn = true;
        System.out.println("TV turned on");
    }

    @Override
    public void turnOff() {
        poweredOn = false;
        System.out.println("TV turned off");
    }

    @Override
    public boolean isPoweredOn() {
        return poweredOn;
    }
}

// Concrete Implementor 2
class Radio implements Device {
    private boolean poweredOn;

    @Override
    public void turnOn() {
        poweredOn = true;
        System.out.println("Radio turned on");
    }

    @Override
    public void turnOff() {
        poweredOn = false;
        System.out.println("Radio turned off");
    }

    @Override
    public boolean isPoweredOn() {
        return poweredOn;
    }
}

// abstraction: RemoteControl
abstract class RemoteControl {
    protected Device device;
    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isPoweredOn()) {
            device.turnOff();
        } else {
            device.turnOn();
        }
    }
}

// Refined Abstraction: AdvancedRemoteControl
class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        if (device.isPoweredOn()) {
            System.out.println("muted.");
        } else {
            System.out.println("can't mute: turned off");
        }
    }
}

public class UniversalRemote {
    public static void main(String[] args) {
        Device tv = new TV();
        Device radio = new Radio();

        AdvancedRemoteControl tvRemote = new AdvancedRemoteControl(tv);
        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(radio);

        tvRemote.togglePower();
        tvRemote.mute();

        radioRemote.togglePower();
        radioRemote.togglePower();
    }
}
