public class MultiModuleDiscount extends Discount{

    private final static double discountAmount = 15.0;
    private static final double Min_Modules = 2.0;

    public MultiModuleDiscount(Cart cart, CourseComponent wrapped) 
    {
        super(cart, wrapped,  discountAmount);
    }

    @Override
    protected boolean evaluateEligibility() 
    {
        return cart.totalModules() >= Min_Modules;
    }
    
}
