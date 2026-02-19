public class DevelopingCountryDiscount extends Discount {

    private final static double discountAmount = 10.0;

    public DevelopingCountryDiscount(Cart cart, CourseComponent wrapped) 
    {
        super(cart, wrapped,  discountAmount);
    }
    
    @Override
    protected boolean evaluateEligibility() 
    {
        return cart.isFromDevelopingCountry();
    }
}
