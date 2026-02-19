public class PracticeSet extends  AddOns{

    private final double price = 10.0;

    public PracticeSet(Module module) 
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
        System.out.println("Added Practice Set: $" + price);
    }
    
}
