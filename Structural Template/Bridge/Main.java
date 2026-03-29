package Bridge;

// Implementor (transport side)
interface Transport {
    void dispatch(String orderId);
    double estimateTime();
}

class BikeTransport implements Transport {
    public void dispatch(String orderId) { System.out.println("Bike dispatching order: " + orderId); }
    public double estimateTime() { return 24; }
}

class DroneTransport implements Transport {
    public void dispatch(String orderId) { System.out.println("Drone dispatching order: " + orderId); }
    public double estimateTime() { return 2; }
}

class RobotTransport implements Transport {
    public void dispatch(String orderId) { System.out.println("Robot dispatching order: " + orderId); }
    public double estimateTime() { return 1; }
}

// Abstraction (delivery type side)
abstract class DeliveryType {
    protected Transport transport; // BRIDGE link

    DeliveryType(Transport transport) { this.transport = transport; }

    abstract void deliver(String orderId);
}

class StandardDelivery extends DeliveryType {
    StandardDelivery(Transport t) { super(t); }
    public void deliver(String orderId) {
        System.out.println("Standard Delivery (24h)");
        transport.dispatch(orderId);
    }
}

class ExpressDelivery extends DeliveryType {
    ExpressDelivery(Transport t) { super(t); }
    public void deliver(String orderId) {
        System.out.println("Express Delivery (4h)");
        transport.dispatch(orderId);
    }
}

class ScheduledDelivery extends DeliveryType {
    String timeSlot;
    ScheduledDelivery(Transport t, String slot) { super(t); this.timeSlot = slot; }
    public void deliver(String orderId) {
        System.out.println("Scheduled Delivery at: " + timeSlot);
        transport.dispatch(orderId);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        DeliveryType d1 = new StandardDelivery(new BikeTransport());
        d1.deliver("ORD001");

        DeliveryType d2 = new ExpressDelivery(new DroneTransport());
        d2.deliver("ORD002");

        DeliveryType d3 = new ScheduledDelivery(new RobotTransport(), "6PM");
        d3.deliver("ORD003");
    }
}