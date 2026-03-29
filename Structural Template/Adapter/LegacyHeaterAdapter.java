package Adapter;

public class LegacyHeaterAdapter implements SmartDevice {
    private LegacyHeater legacyHeater;

    public LegacyHeaterAdapter() {
        legacyHeater = new LegacyHeater();
    }

    @Override
    public void turnOn() {
        legacyHeater.startHeating();
    }

    @Override
    public void turnOff() {
        legacyHeater.stopHeating();
    }
    
}
