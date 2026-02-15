package Observer;

import java.util.ArrayList;
import java.util.List;

public class RavenBoard implements Publisher {

    private List<Subscriber> subscribers;
    
    public RavenBoard()
    {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void attach(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println(subscriber.getClass().getSimpleName() + " subscribed to the Raven Board");
    }

    @Override
    public void detach(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.getClass().getSimpleName() + " unsubscribed from the Raven Board");
    }

    @Override
    public void notifySubscribers(String message) 
    {
        System.out.println("\n🦅 RAVEN DELIVERS SCROLL: \"" + message + "\"");
        System.out.println("-------------------------------------------");
        for(Subscriber subscriber : subscribers)
        {
            subscriber.update(message);
        }
        System.out.println("-------------------------------------------\n");
        
    }

    // Method to post new message
    public void postMessage(String message) {
        System.out.println("📜 New scroll posted to the board!");
        notifySubscribers(message);
    }

    
}
