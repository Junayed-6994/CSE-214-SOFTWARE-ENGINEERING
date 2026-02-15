package State;

public class RedLightState implements TrafficLightState {

    @Override
    public void handle(TrafficLightContext context)
    {
        System.out.println("Red Light Duration : 5 seconds");

        try 
        {
            Thread.sleep(5000); // Wait for 5 seconds
        }
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
        }

        System.out.println("Switching to Yellow");
        context.setState(new YellowLightState());
    }
    
}
