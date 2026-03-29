package Decorator;

public class SeasonalDiscount extends DiscountDecorator {

    public SeasonalDiscount(Purchase purchase)
    {
        super(purchase);
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice() - 100;
    }
    
}
