public class SpecialDiscount extends Discount {

    private final static double discountAmount = 12.0;
    private static final double Min_Duration_Hrs = 5.0;

    public SpecialDiscount(Cart cart, CourseComponent wrapped) 
    {
        super(cart, wrapped,  discountAmount);
    }
    
    @Override
    protected boolean evaluateEligibility() 
    {
        return cart.totalDuration() >= Min_Duration_Hrs;
    }
    
}
