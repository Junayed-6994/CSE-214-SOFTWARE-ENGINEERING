package Decorator;

public abstract class DiscountDecorator implements Purchase{
    
    private Purchase purchase;

    public DiscountDecorator(Purchase purchase) {
        this.purchase = purchase;
    }

    public double calculatePrice() {
        return purchase.calculatePrice();
    }
}
