public abstract class Discount implements CourseComponent {
    
    protected Cart cart;
    protected CourseComponent wrapped;
    private final double discountAmount;

    public Discount(Cart cart, CourseComponent wrapped, double discountAmount) 
    {
        this.cart = cart;
        this.wrapped = wrapped;
        this.discountAmount = discountAmount;
    }

    protected abstract boolean evaluateEligibility();

    public double effectiveDiscount()
    {
        if (evaluateEligibility()) 
        {
            return discountAmount;
        }
        return 0.0;
    }

    @Override
    public double calculatePrice()
    {
        return Math.max(0,wrapped.calculatePrice() - effectiveDiscount());
    }

    @Override
    public double calculateDuration() 
    {
        return wrapped.calculateDuration();
    }

    @Override
    public String getName()
    {
        return wrapped.getName() + " (Discount Applied: $" + effectiveDiscount() + ")";
    }

    @Override
    public void displayDetails() 
    {
        wrapped.displayDetails();
        if (effectiveDiscount() > 0) 
        {
            System.out.println("Discount Applied: $" + effectiveDiscount());
            System.out.println("Price after Discount: $" + calculatePrice());
        } 
        else 
        {
            System.out.println("No Discount Applied");
        }
    }


    
}
