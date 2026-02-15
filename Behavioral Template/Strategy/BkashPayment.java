package Strategy;

public class BkashPayment implements PaymentStrategy{
    private String mobileNumber;
    private String pin;
    
    public BkashPayment(String mobileNumber, String pin) {
        this.mobileNumber = mobileNumber;
        this.pin = pin;
    }

    @Override
    public boolean validate() {
        System.out.println("Validating BKash account...");
        // Simulate validation logic
        if (mobileNumber.length() == 11 && pin.length() == 4) {
            System.out.println("BKash account validated successfully");
            return true;
        }
        System.out.println("Invalid BKash details");
        return false;
    }
    
    @Override
    public void pay(double amount) {
        if (validate()) {
            System.out.println("Processing BKash payment...");
            System.out.println(" Mobile: " + mobileNumber);
            System.out.println("Amount: ৳" + amount);
            System.out.println("Payment of ৳" + amount + " successful via BKash!");
        }
    }
}
