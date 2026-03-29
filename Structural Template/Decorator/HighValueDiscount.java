package Decorator;

public class HighValueDiscount extends DiscountDecorator
{
    public HighValueDiscount(Purchase purchase) {
        super(purchase);
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice() * 0.98;
    }
}
