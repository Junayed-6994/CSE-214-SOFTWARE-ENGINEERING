package Observer;

public class SupplyTeam implements Subscriber {
    
    private String teamId;
    
    public SupplyTeam(String teamId) {
        this.teamId = teamId;
    }
    
    @Override
    public void update(String message)
    {
        System.out.println("Supply Team (" + teamId + ") received: \"" + message + "\"");
        
        // Supply Team's specific response
        if (message.toLowerCase().contains("winter") || message.toLowerCase().contains("supplies")) 
        {
            System.out.println("Supply Team responds: \"Update inventory! Checking stock levels!\"");
        } 
        
        else if (message.toLowerCase().contains("ships")) 
        {
            System.out.println("Supply Team responds: \"Preparing to receive cargo!\"");
        } 
        
        else 
        {
            System.out.println(" Supply Team responds: \"Message received. No action needed.\"");
        }
    }
}
