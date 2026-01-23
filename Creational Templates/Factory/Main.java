package Factory;

/*
 * Product interface - common interface for all transports
*/
interface Transport {
    void deliver();
}


/**
 * Concrete Product 1 - Truck
 */
class Truck implements Transport {
    public void deliver() {
        System.out.println("Delivering by Truck on the road");
    }
}


/**
 * Concrete Product 2 - Ship
 */
class Ship implements Transport {
    public void deliver() {
        System.out.println("Delivering by Ship on the sea");
    }
}


/**
 * Concrete Product 3 - Airplane
 */
class Airplane implements Transport {
    public void deliver() {
        System.out.println("Delivering by Airplane in the air");
    }
}


/**
 * Abstract Creator - defines factory method
 */
abstract class TransportFactory {
    public abstract Transport createTransport();
}


/**
 * Concrete Factory 1 - creates Truck
 */
class RoadTransportFactory extends TransportFactory {
    public Transport createTransport() {
        return new Truck();
    }
}


/**
 * Concrete Factory 2 - creates Ship
 */
class SeaTransportFactory extends TransportFactory {
    public Transport createTransport() {
        return new Ship();
    }
}


/**
 * Concrete Factory 3 - creates Airplane
 */
class AirTransportFactory extends TransportFactory {
    public Transport createTransport() {
        return new Airplane();
    }
}


/**
 * Main - client code
 */
public class Main {
    public static void main(String[] args) {
        // Create factories
        TransportFactory roadFactory = new RoadTransportFactory();
        TransportFactory seaFactory = new SeaTransportFactory();
        TransportFactory airFactory = new AirTransportFactory();

        // Use factories to create transports
        Transport t1 = roadFactory.createTransport();
        t1.deliver();

        Transport t2 = seaFactory.createTransport();
        t2.deliver();

        Transport t3 = airFactory.createTransport();
        t3.deliver();
    }
}
