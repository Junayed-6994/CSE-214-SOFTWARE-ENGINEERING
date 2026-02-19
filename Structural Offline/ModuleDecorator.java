public abstract class ModuleDecorator extends Module{
    protected Module wrappedModule;

    public ModuleDecorator(Module module) 
    {
        super(module.getName());
        this.wrappedModule = module;
    }

    @Override
    public double calculatePrice()
    {
        return wrappedModule.calculatePrice();
    }

    @Override
    public double calculateDuration() 
    {
        return wrappedModule.calculateDuration();
    }

    @Override
    public String getName() 
    {
        return wrappedModule.getName();
    }

    @Override
    public void displayDetails() 
    {
        wrappedModule.displayDetails();
    }

    


}