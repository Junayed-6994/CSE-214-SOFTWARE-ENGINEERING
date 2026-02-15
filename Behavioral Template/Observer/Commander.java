package Observer;

public class Commander implements Subscriber {

    private String name;

    public Commander(String name)
    {
        this.name = name;
    }

    @Override
    public void update(String message)
    {
        System.out.println("Commander " + name + " received: \"" + message + "\"") ;

        if (message.toLowerCase().contains("enemy")) 
        {
            System.out.println("Commander " + name + " responds: \"Dispatch riders! Prepare defenses!\"");
        } 

        else if (message.toLowerCase().contains("ships"))
        {
            System.out.println("Commander " + name + " responds: \"Ready the harbor! Prepare to dock!\"");
        } 

        else 
        {
            System.out.println("Commander " + name + " responds: \"Acknowledged. Standing by.\"");
        }
    }
    
}
