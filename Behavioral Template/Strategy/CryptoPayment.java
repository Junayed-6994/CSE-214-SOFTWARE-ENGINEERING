package Strategy;

public class CryptoPayment implements PaymentStrategy {

    private String walletAddress;
    private String cryptoType;
    
    public CryptoPayment(String walletAddress, String cryptoType) {
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;
    }
    
    @Override
    public boolean validate() {
        System.out.println("₿ Validating " + cryptoType + " wallet...");
        // Simulate validation logic
        if (walletAddress.length() >= 26) {
            System.out.println("" + cryptoType + " wallet validated successfully");
            return true;
        }
        System.out.println("Invalid wallet address");
        return false;
    }
    
    @Override
    public void pay(double amount) {
        if (validate()) {
            System.out.println("Processing " + cryptoType + " payment...");
            System.out.println("Wallet: " + walletAddress.substring(0, 10) + "...");
            System.out.println("Amount: " + amount + " " + cryptoType);
            System.out.println("Payment of " + amount + " " + cryptoType + " successful!");
        }
    }
    
}
