package Composite;

public class GroceryItem implements BazarItem
{
    String name;
    double price,weight;

    public GroceryItem(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void display() {
        System.out.println("Grocery Item: " + name + ", Price: " + price + ", Weight: " + weight);
    }
    
}
