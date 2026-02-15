package Mediator;

// Main Class
public class SmartHomeAutomation {
    public static void main(String[] args) {
        CentralHub hub = new CentralHub();
        
        LightSensor lightSensor = new LightSensor(hub);
        AutomaticBlinds blinds = new AutomaticBlinds(hub);
        AirConditioner airConditioner = new AirConditioner(hub);
        
        hub.registerLightSensor(lightSensor);
        hub.registerAutomaticBlinds(blinds);
        hub.registerAirConditioner(airConditioner);
        
        System.out.println("=== Smart Home Automation System ===\n");
        
        lightSensor.detectBrightness("High");
        
        System.out.println("\n=== End of Simulation ===");
    }
}