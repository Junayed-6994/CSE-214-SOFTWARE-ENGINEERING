
interface NotificationStrategy {
    void send(String message);
}

class EmailNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class AppNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending APP notification: " + message);
    }
}

class NotificationService {

    private NotificationStrategy strategy;

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void notifyUser(String message) {
        strategy.send(message);
    }
}

public class NotificationSystem {
    public static void main(String[] args) {

        NotificationService service = new NotificationService();

        service.setStrategy(new EmailNotification());
        service.notifyUser("Transaction of $500 completed.");

        service.setStrategy(new SMSNotification());
        service.notifyUser("Low balance alert!");

        service.setStrategy(new AppNotification());
        service.notifyUser("Special promotional offer!");
    }
}



