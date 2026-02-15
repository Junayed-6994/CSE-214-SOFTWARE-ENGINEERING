package Strategy;

public class PaymentService {
    
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount)
    {
        if (paymentStrategy == null) 
        {
            System.out.println("No payment method selected!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        paymentStrategy.pay(amount);
        System.out.println("=".repeat(50) + "\n");
    }
    
}
