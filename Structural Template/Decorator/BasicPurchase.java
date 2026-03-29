package Decorator;

//Concrete Component
public class BasicPurchase implements Purchase
{
    private double price;

    public BasicPurchase(double price) {
        this.price = price;
    }

    @Override
    public double calculatePrice() {
        return price;
    }
}
