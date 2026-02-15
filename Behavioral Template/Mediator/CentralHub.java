package Mediator;

public class CentralHub implements Mediator{

    private Component LightSensor;
    private Component AutomaticBlinds;
    private Component AirConditioner;

    public void registerLightSensor(Component LightSensor)
    {
        this.LightSensor = LightSensor;
    }
    public void registerAutomaticBlinds(Component AutomaticBlinds)
    {
        this.AutomaticBlinds = AutomaticBlinds;
    }
    public void registerAirConditioner(Component AirConditioner)
    {
        this.AirConditioner = AirConditioner;
    }

    @Override
    public void notify(Component sender, String event)
    {
        if(sender == LightSensor && event.equals("High Brightness"))
        {
            System.out.println("Hub: Light Sensor detected high brightness");
            System.out.println("Hub: Telling Blinds to close");
            AutomaticBlinds.receive("close");
        }

        else if(sender == AutomaticBlinds && event.equals("Closed"))
        {
            System.out.println("Hub: Blinds have closed");
            System.out.println("Hub: Telling Air Conditioner to turn on");
            AirConditioner.receive("turn_on");

        }
    }
    
}
