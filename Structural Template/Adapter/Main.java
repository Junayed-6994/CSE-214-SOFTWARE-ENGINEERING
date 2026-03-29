package Adapter;

public class Main {
    public static void main(String[] args) {

        SmartDevice smartBulb = new OldSmartBulbAdapter();
        smartBulb.turnOn();
        smartBulb.turnOff();

        SmartDevice smartHeater = new LegacyHeaterAdapter();
        smartHeater.turnOn();
        smartHeater.turnOff();
    }
}
