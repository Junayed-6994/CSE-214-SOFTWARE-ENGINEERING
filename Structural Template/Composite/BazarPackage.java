package Composite;

import java.util.ArrayList;
import java.util.List;

public class BazarPackage implements BazarItem
{

    String name;
    List<BazarItem> items = new ArrayList<>();

    public BazarPackage(String name) {
        this.name = name;
    }

    public void add(BazarItem item) {
        items.add(item);
    }

    public void removE(BazarItem item) {
        items.remove(item);
    }

    @Override
    public double getPrice()
    {
        double total = 0;
        for (BazarItem item : items) {
            total += item.getPrice();
        }

        return total;
    }

    @Override
    public double getWeight()
    {
        double total = 0;
        for (BazarItem item : items) {
            total += item.getWeight();
        }

        return total;
    }

    @Override
    public void display() {
        System.out.println("Bazar Package: " + name);
        for (BazarItem item : items) {
            item.display();
        }
    }
    
}
