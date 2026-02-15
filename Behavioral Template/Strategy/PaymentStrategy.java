package Strategy;

interface PaymentStrategy {
    void pay (double amount);
    boolean validate();
}
