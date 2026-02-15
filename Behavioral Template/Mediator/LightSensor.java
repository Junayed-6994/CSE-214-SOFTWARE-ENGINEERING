package Mediator;

public class LightSensor extends Component {

    public LightSensor(Mediator mediator)
    {
        super(mediator);
    }

    public void detectBrightness(String level)
    {
        System.out.println("Light Sensor: Detected " + level + " brightness");
        if (level.equals("High")) {
            mediator.notify(this, "High Brightness");
        }
    }

    @Override
    public void receive(String message) 
    {
        // Light sensor doesn't receive commands
    }
    
}
