package State;

public class GreenLightState implements TrafficLightState {
    @Override
    public void handle(TrafficLightContext context)
    {
        System.out.println("Green Light Duration : 10 seconds");

        try 
        {
            Thread.sleep(10000); // Wait for 2 seconds
        }
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
        }

        System.out.println("Switching to Red");
        context.setState(new RedLightState());
    }
}
