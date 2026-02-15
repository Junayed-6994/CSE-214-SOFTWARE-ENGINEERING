
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String condition, double temperature);
}

interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}



class WeatherStation implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private String condition;
    private double temperature;

    public void setWeather(String condition, double temperature) {
        this.condition = condition;
        this.temperature = temperature;

        System.out.println("\nWeather updated:");
        System.out.println("Condition: " + condition);
        System.out.println("Temperature: " + temperature + "°C");

        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(condition, temperature);
        }
    }
}

class UserDevice implements Observer {

    private String username;

    public UserDevice(String username) {
        this.username = username;
    }

    @Override
    public void update(String condition, double temperature) {

        System.out.println(username + " received update -> "
                + condition + ", " + temperature + "°C");

        // Severe weather alerts
        if (condition.equalsIgnoreCase("storm") ||
            condition.equalsIgnoreCase("heavy snow")) {

            System.out.println("⚠ ALERT for " + username + ": Severe weather warning!");
        }
    }
}



public class Weather {
    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        // Create users
        UserDevice user1 = new UserDevice("Alice");
        UserDevice user2 = new UserDevice("Bob");

        // Subscribe users
        station.addObserver(user1);
        station.addObserver(user2);

        // Initial weather
        station.setWeather("Sunny", 30);

        // Weather changes
        station.setWeather("Rainy", 20);

        // Severe weather
        station.setWeather("Storm", 18);
    }
}



