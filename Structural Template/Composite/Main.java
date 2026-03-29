package Composite;

public class Main {
    public static void main(String[] args) {

        // Individual items (Leaves)
        GroceryItem rice  = new GroceryItem("Rice",  80, 5);
        GroceryItem oil   = new GroceryItem("Oil",   60, 2);
        GroceryItem pulse = new GroceryItem("Pulse", 50, 1);
        GroceryItem milk  = new GroceryItem("Milk",  40, 1);
        GroceryItem sugar = new GroceryItem("Sugar", 30, 2);

        // Preset Package - Small
        BazarPackage small = new BazarPackage("Small");
        small.add(rice);
        small.add(oil);

        // Preset Package - Family
        BazarPackage family = new BazarPackage("Family");
        family.add(rice);
        family.add(oil);
        family.add(pulse);
        family.add(milk);

        // Preset Package - Mega
        BazarPackage mega = new BazarPackage("Mega");
        mega.add(rice);
        mega.add(oil);
        mega.add(pulse);
        mega.add(milk);
        mega.add(sugar);

        // Custom Package (mix of preset + single items)
        BazarPackage custom = new BazarPackage("Custom Bazar");
        custom.add(small);   // adding a preset package inside!
        custom.add(sugar);   // adding a single item

        // Display all
        System.out.println("=== Small Package ===");
        small.display();

        System.out.println("\n=== Family Package ===");
        family.display();

        System.out.println("\n=== Mega Package ===");
        mega.display();

        System.out.println("\n=== Custom Package ===");
        custom.display();
    }
}