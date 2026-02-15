package Strategy;

public class ECommercePaymentSystem {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║     E-COMMERCE PAYMENT PROCESSING SYSTEM      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // Create checkout context
        PaymentService checkout = new PaymentService();
        
        // Scenario 1: Customer pays with Credit Card
        System.out.println("🛒 SCENARIO 1: Customer selects Credit Card");
        System.out.println("-".repeat(50));
        PaymentStrategy creditCard = new CreditCardPayment(
            "1234567812345678", 
            "123", 
            "12/25", 
            "John Doe"
        );
        checkout.setPaymentStrategy(creditCard);
        checkout.executePayment(150.99);
        
        // Scenario 2: Customer switches to BKash
        System.out.println("🛒 SCENARIO 2: Customer switches to BKash");
        System.out.println("-".repeat(50));
        PaymentStrategy bkash = new BkashPayment("01712345678", "1234");
        checkout.setPaymentStrategy(bkash);
        checkout.executePayment(5000.00);
        
        // Scenario 3: Customer chooses Cryptocurrency
        System.out.println("🛒 SCENARIO 3: Customer chooses Bitcoin");
        System.out.println("-".repeat(50));
        PaymentStrategy crypto = new CryptoPayment(
            "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", 
            "Bitcoin"
        );
        checkout.setPaymentStrategy(crypto);
        checkout.executePayment(0.005);
        
        
        // Scenario 5: No payment method selected
        System.out.println("🛒 SCENARIO 5: Customer forgot to select payment method");
        System.out.println("-".repeat(50));
        PaymentService emptyCheckout = new PaymentService();
        emptyCheckout.executePayment(100.00);
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           END OF PAYMENT PROCESSING           ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }
}
