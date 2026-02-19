public abstract class ModuleDecorator implements ModuleComponent
{
    protected ModuleComponent wrappedModule;

    public ModuleDecorator(ModuleComponent module) 
    {
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