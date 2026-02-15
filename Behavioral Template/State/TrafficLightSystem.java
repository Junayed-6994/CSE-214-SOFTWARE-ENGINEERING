package State;

public class TrafficLightSystem {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    TRAFFIC LIGHT CONTROL SYSTEM       ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        TrafficLightContext trafficLight = new TrafficLightContext();
        
        // Run 3 complete cycles
        for (int cycle = 1; cycle <= 3; cycle++) {
            trafficLight.changeLight();
        }
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    TRAFFIC LIGHT SYSTEM STOPPED       ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}