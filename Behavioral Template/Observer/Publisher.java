package Observer;

// Subject Interface (Publisher)
public interface Publisher {
    void attach(Subscriber subscriber);
    void detach(Subscriber subscriber);
    void notifySubscribers(String message);
}
