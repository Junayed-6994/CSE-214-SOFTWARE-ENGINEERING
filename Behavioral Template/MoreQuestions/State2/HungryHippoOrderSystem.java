package MoreQuestions.State2;



// Context Class - Order
class OrderContext {
    private OrderState currentState;
    private String orderId;
    
    public OrderContext(String orderId) {
        this.orderId = orderId;
        this.currentState = new PlacedState(); // Start with Placed
    }
    
    public void setState(OrderState state) {
        this.currentState = state;
    }
    
    public void getStatus() {
        System.out.println("\n🆔 Order ID: " + orderId);
        currentState.printStatus();
    }
    
    public void nextStep() {
        currentState.next(this);
    }
    
    public void cancelOrder() {
        currentState.cancel(this);
    }
}

// State Interface
interface OrderState {
    void next(OrderContext context);
    void cancel(OrderContext context);
    void printStatus();
}


// Concrete State 1 - Placed
class PlacedState implements OrderState {
    
    @Override
    public void printStatus() {
        System.out.println("📋 Order Status: PLACED");
        System.out.println("   Waiting for restaurant confirmation...");
    }
    
    @Override
    public void next(OrderContext context) {
        System.out.println("✅ Restaurant confirmed the order!");
        context.setState(new ConfirmedState());
    }
    
    @Override
    public void cancel(OrderContext context) {
        System.out.println("❌ Order cancelled by customer");
        context.setState(new CancelledState());
    }


}

// Concrete State 2 - Confirmed
class ConfirmedState implements OrderState {
    
    @Override
    public void printStatus() {
        System.out.println("✅ Order Status: CONFIRMED");
        System.out.println("   Restaurant is preparing your food...");
    }
    
    @Override
    public void next(OrderContext context) {
        System.out.println("🚚 Order picked up by delivery rider!");
        context.setState(new ShippedState());
    }
    
    @Override
    public void cancel(OrderContext context) {
        System.out.println("❌ Order cancelled before shipment");
        context.setState(new CancelledState());
    }
}


// Concrete State 3 - Shipped
class ShippedState implements OrderState {
    
    @Override
    public void printStatus() {
        System.out.println("🚚 Order Status: SHIPPED");
        System.out.println("   Your food is on the way...");
    }
    
    @Override
    public void next(OrderContext context) {
        System.out.println("🎉 Order delivered successfully!");
        context.setState(new DeliveredState());
    }
    
    @Override
    public void cancel(OrderContext context) {
        System.out.println("⚠️  Cannot cancel - Order already shipped!");
    }
}

// Concrete State 4 - Delivered
class DeliveredState implements OrderState {
    
    @Override
    public void printStatus() {
        System.out.println("🎉 Order Status: DELIVERED");
        System.out.println("   Enjoy your meal!");
    }
    
    @Override
    public void next(OrderContext context) {
        System.out.println("⚠️  Order already delivered. No further action possible.");
    }
    
    @Override
    public void cancel(OrderContext context) {
        System.out.println("⚠️  Cannot cancel - Order already delivered!");
    }
}


// Concrete State 5 - Cancelled
class CancelledState implements OrderState {
    
    @Override
    public void printStatus() {
        System.out.println("❌ Order Status: CANCELLED");
        System.out.println("   Your order has been cancelled");
    }
    
    @Override
    public void next(OrderContext context) {
        System.out.println("⚠️  Cannot proceed - Order is cancelled");
    }
    
    @Override
    public void cancel(OrderContext context) {
        System.out.println("⚠️  Order is already cancelled");
    }
}


public class HungryHippoOrderSystem {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║      HUNGRYHIPPO ORDER MANAGEMENT SYSTEM     ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        // Scenario 1: Successful Order Journey
        System.out.println("\n========== SCENARIO 1: SUCCESSFUL DELIVERY ==========");
        OrderContext order1 = new OrderContext("HH-12345");
        
        order1.getStatus();
        System.out.println();
        
        order1.nextStep(); // Placed → Confirmed
        order1.getStatus();
        System.out.println();
        
        order1.nextStep(); // Confirmed → Shipped
        order1.getStatus();
        System.out.println();
        
        order1.nextStep(); // Shipped → Delivered
        order1.getStatus();
        System.out.println();
        
        // Try to proceed after delivery
        order1.nextStep(); // Should show error
        
        // Scenario 2: Order Cancelled Before Shipment
        System.out.println("\n\n========== SCENARIO 2: CANCELLED BEFORE SHIPMENT ==========");
        OrderContext order2 = new OrderContext("HH-67890");
        
        order2.getStatus();
        System.out.println();
        
        order2.nextStep(); // Placed → Confirmed
        order2.getStatus();
        System.out.println();
        
        order2.cancelOrder(); // Confirmed → Cancelled
        order2.getStatus();
        System.out.println();
        
        // Try to proceed after cancellation
        order2.nextStep(); // Should show error
        
        // Scenario 3: Cannot Cancel After Shipment
        System.out.println("\n\n========== SCENARIO 3: CANNOT CANCEL AFTER SHIPMENT ==========");
        OrderContext order3 = new OrderContext("HH-11111");
        
        order3.getStatus();
        System.out.println();
        
        order3.nextStep(); // Placed → Confirmed
        order3.nextStep(); // Confirmed → Shipped
        order3.getStatus();
        System.out.println();
        
        order3.cancelOrder(); // Should show error - cannot cancel after shipped
        
        // Scenario 4: Early Cancellation (from Placed)
        System.out.println("\n\n========== SCENARIO 4: EARLY CANCELLATION ==========");
        OrderContext order4 = new OrderContext("HH-22222");
        
        order4.getStatus();
        System.out.println();
        
        order4.cancelOrder(); // Placed → Cancelled
        order4.getStatus();
        
        System.out.println("\n\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           END OF ORDER DEMONSTRATIONS        ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }
}