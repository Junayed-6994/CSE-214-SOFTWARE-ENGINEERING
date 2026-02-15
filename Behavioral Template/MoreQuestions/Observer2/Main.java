

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String stockName, double price);
}

interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

class Stock implements Subject {

    private String name;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(name, price);
        }
    }
}

class User implements Observer {

    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(username + " has been notified: The price of "
                + stockName + " is now " + price);
    }
}


public class Main {
    public static void main(String[] args) {

        // Create stocks
        Stock googleStock = new Stock("Google", 1500);
        Stock appleStock = new Stock("Apple", 1200);

        // Create users
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        // Follow stocks
        googleStock.attach(user1);
        googleStock.attach(user2);
        appleStock.attach(user1);

        // Price changes
        System.out.println("Updating Google stock price...");
        googleStock.setPrice(1550);

        System.out.println("\nUpdating Apple stock price...");
        appleStock.setPrice(1250);

        // Unfollow
        googleStock.detach(user1);

        System.out.println("\nUpdating Google stock price again...");
        googleStock.setPrice(1600);
    }
}



