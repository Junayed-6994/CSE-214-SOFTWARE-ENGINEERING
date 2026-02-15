package Mediator;

public class AirConditioner extends Component {
    
    public AirConditioner(Mediator mediator) {
        super(mediator);
    }
    
    @Override
    public void receive(String message) {
        if (message.equals("turn_on")) {
            turnOn();
        }
    }
    
    private void turnOn() {
        System.out.println("Air Conditioner: Turning on (room will get stuffy)");
    }
}
