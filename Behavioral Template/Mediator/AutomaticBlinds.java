package Mediator;

public class AutomaticBlinds extends Component{

    public AutomaticBlinds(Mediator mediator)
    {
        super(mediator);
    }

    @Override
    public void receive(String message) 
    {
        if(message.equals("close"))
        {
            close();
        }
    }

    private void close()
    {
        System.out.println("Blinds: Closing...");
        mediator.notify(this, "Closed");
    }
}
