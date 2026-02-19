public class CartDiscount implements CourseComponent {
    
    private Cart cart;
    public CartDiscount(Cart cart) 
    {
        this.cart = cart;
    }

    @Override
    public double calculatePrice() 
    {
        return cart.totalPrice();
    }

    @Override
    public double calculateDuration() 
    {
        return cart.totalDuration();
    }

    @Override
    public String getName() 
    {
        return "Cart Summary";
    }

    @Override
    public void displayDetails()
    {
        System.out.println("Cart Summary:");
        System.out.println("Total Duration: " + calculateDuration() + " hours");
        System.out.println("Total Price: $" + calculatePrice());
    }
}
