package Adapter;

public class OldSmartBulbAdapter implements SmartDevice {
    private OldSmartBulb oldBulb;

    public OldSmartBulbAdapter() {
        oldBulb = new OldSmartBulb();
    }

    @Override
    public void turnOn() {
        oldBulb.powerOn();
    }

    @Override
    public void turnOff() {
        oldBulb.powerOff();
    }
    
}
