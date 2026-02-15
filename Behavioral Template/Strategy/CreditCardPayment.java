package Strategy;

public class CreditCardPayment implements PaymentStrategy
{
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardHolderName;
    
    public CreditCardPayment(String cardNumber, String cvv, String expiryDate, String cardHolderName)
    {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        if(validate())
        {
            System.out.println("Processing Credit Card payment...");
            System.out.println("Card Holder: " + cardHolderName);
            System.out.println("Card Number: **** **** **** " + cardNumber.substring(12));
            System.out.println("Amount: $" + amount);
            System.out.println("Payment of $" + amount + " successful via Credit Card!" + expiryDate);
        }
        
    }
    @Override
    public boolean validate() 
    {
        if(cardNumber.length()==16 && cvv.length()==3)
        {
            System.out.println("Credit Card Validated Succesfully");
            return true;
        }
        System.out.println("Invalid Credit Card Details");
        return false;
    }
}