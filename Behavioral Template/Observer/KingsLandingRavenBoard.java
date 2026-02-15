package Observer;

// Main Class - Demonstration
public class KingsLandingRavenBoard {
    public static void main(String[] args) {
        System.out.println("=== KING'S LANDING RAVEN BOARD SYSTEM ===\n");
        
        // Create the Raven Board (Subject)
        RavenBoard ravenBoard = new RavenBoard();
        
        // Create Observers
        Commander commander = new Commander("Jon Snow");
        Scouts scouts = new Scouts("Northern Scouts");
        SupplyTeam supplyTeam = new SupplyTeam("Team Alpha");
        
        System.out.println("--- Initial Subscriptions ---");
        // Subscribe observers to the board
        ravenBoard.attach(commander);
        ravenBoard.attach(scouts);
        ravenBoard.attach(supplyTeam);
        
        System.out.println("\n========================================");
        System.out.println("        MESSAGE DEMONSTRATIONS");
        System.out.println("========================================");
        
        // Message 1: Enemy spotted
        ravenBoard.postMessage("Enemy spotted near the river");
        
        // Message 2: Winter supplies
        ravenBoard.postMessage("Winter supplies running low");
        
        // Demonstrate unsubscribe
        System.out.println("\n--- Scouts Leaving the Board Room ---");
        ravenBoard.detach(scouts);
        
        // Message 3: Ships seen (Scouts won't receive this)
        ravenBoard.postMessage("Ships seen in the east");
        
        System.out.println("\n========================================");
        System.out.println("     END OF DEMONSTRATION");
        System.out.println("========================================");
    }
}
