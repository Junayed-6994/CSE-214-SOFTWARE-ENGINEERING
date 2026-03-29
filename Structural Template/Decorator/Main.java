package Decorator;

public class Main {
    
    public static void main(String[] args) {
        double basePrice = 12000;

        Purchase purchase = new BasicPurchase(basePrice);

        // Apply discounts (wrap one on top of another)
        Purchase discountedPurchase = new LoyaltyDiscount(purchase);
        discountedPurchase = new SeasonalDiscount(discountedPurchase);
        discountedPurchase = new HighValueDiscount(discountedPurchase);

        double finalPrice = discountedPurchase.calculatePrice();
        System.out.println("Final price after all discounts: " + finalPrice);
    }
}
