public class LiveMentorSupport extends AddOns {

    private final double price = 20.0;

    public LiveMentorSupport(Module module) 
    {
        super(module);
    }

    @Override
    public double calculatePrice() 
    {
        return wrappedModule.calculatePrice() + price;
    }

    @Override
    public void displayDetails() 
    {
        super.displayDetails();
        System.out.println("Added Live Mentor Support: $" + price);
    }
    
}
