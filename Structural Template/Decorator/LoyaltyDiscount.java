package Decorator;

public class LoyaltyDiscount extends DiscountDecorator
{
    public LoyaltyDiscount(Purchase purchase) {
        super(purchase);
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice() * 0.9; // Apply a 10% discount
    }
    
}
