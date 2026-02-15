package Observer;

public class Scouts implements Subscriber {

    private String groupName;

    public Scouts(String groupName)
    {
        this.groupName = groupName;
    }

    @Override
    public void update(String message)
    {
        System.out.println("Scouts (" + groupName + ") received: \"" + message + "\"");
        
        // Scouts' specific response
        if (message.toLowerCase().contains("enemy")) 
        {
            System.out.println("Scouts respond: \"Dispatch riders! Investigating threat!\"");
        } 
        
        else if (message.toLowerCase().contains("ships"))
        {
            System.out.println("Scouts respond: \"Moving to coastline for reconnaissance!\"");
        } 
        
        else 
        {
            System.out.println(" Scouts respond: \"Information noted. Continuing patrol.\"");
        }
    }
}
    

