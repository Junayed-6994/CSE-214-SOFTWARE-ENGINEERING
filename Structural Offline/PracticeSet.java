public class PracticeSet extends ModuleDecorator{

    private final double price = 10.0;

    public PracticeSet(ModuleComponent module) 
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
        System.out.println("Added Practice Set: $" + price + " on  module " + wrappedModule.getName());
        System.out.println("-----------------------------");
    }
    
}
