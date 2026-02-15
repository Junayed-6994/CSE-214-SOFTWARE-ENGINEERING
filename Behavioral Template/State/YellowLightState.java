package State;

public class YellowLightState implements TrafficLightState{

    @Override
    public void handle(TrafficLightContext context)
    {
        System.out.println("Yellow Light Duration : 2 seconds");

        try 
        {
            Thread.sleep(2000); // Wait for 2 seconds
        }
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
        }

        System.out.println("Switching to Green");
        context.setState(new GreenLightState());
    }
    
}
