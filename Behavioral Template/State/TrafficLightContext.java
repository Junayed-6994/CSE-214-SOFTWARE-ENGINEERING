package State;

public class TrafficLightContext {

    private TrafficLightState currentState;

    public TrafficLightContext() {
        this.currentState = new RedLightState();
    }

    public void setState(TrafficLightState state)
    {
        this.currentState = state;
    }

    public void changeLight()
    {
        currentState.handle(this);
        currentState.handle(this);
        currentState.handle(this);
    }
    
    
}
