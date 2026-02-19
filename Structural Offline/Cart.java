import java.util.List;
import java.util.ArrayList;

public class Cart {
    
    private List<CourseComponent> contents;
    private boolean isFromDevelopingCountry;

    public Cart(boolean isFromDevelopingCountry) 
    {
        this.contents = new ArrayList<>();
        this.isFromDevelopingCountry = isFromDevelopingCountry;
    }

    public void addItems(CourseComponent item) 
    {
        if( !(item instanceof Lesson || item instanceof Module || item instanceof Course) )
        {
            throw new IllegalArgumentException("Only Lessons ,Courses and Modules can be added to the cart.");
        }

        contents.add(item);
    }

    public void removeItems(CourseComponent item) 
    {
        contents.remove(item);
    }

    public double totalPrice()
    {
        double price = 0;
        for (CourseComponent item : contents) 
        {
            price += item.calculatePrice();
        }
        
        return price;
    }

    public double totalDuration() 
    {
        double duration = 0;
        for (CourseComponent item : contents) 
        {
            duration += item.calculateDuration();
        }
        return duration;
    }

    public boolean isFromDevelopingCountry() 
    {
        return isFromDevelopingCountry;
    }

    public int totalModules() 
    {
        int count = 0;
        for (CourseComponent item : contents) 
        {
            if (item instanceof Module) 
            {
                count++;
            }
        }
        return count;
    }


    //need to see discount on each component of the cart

    public void generateReceipt(){

        for (CourseComponent item : contents) 
        {
            item.displayDetails();
        }
        
        CartDiscount cartDiscount = new CartDiscount(this);
        cartDiscount.displayDetails();

        double total_discount_amount = 0;

        Discount overallDiscount = new MultiModuleDiscount(this, cartDiscount);
        if(overallDiscount.evaluateEligibility())
        {
            System.out.println("You have got a discount of $" + overallDiscount.effectiveDiscount() + " on your cart due to purchasing 2 or more modules!");
            total_discount_amount += overallDiscount.effectiveDiscount();
        }

        overallDiscount = new SpecialDiscount(this, cartDiscount);
        if(overallDiscount.evaluateEligibility())
        {
            System.out.println("You have got a discount of $" + overallDiscount.effectiveDiscount() + " on your cart due to purchasing items with total duration of 5 or more hours!");
            total_discount_amount += overallDiscount.effectiveDiscount();
        }

        overallDiscount = new DevelopingCountryDiscount(this, cartDiscount);
        if(overallDiscount.evaluateEligibility())
        {
            System.out.println("You have got a discount of $" + overallDiscount.effectiveDiscount() + " on your cart due to being from a developing country!");
            total_discount_amount += overallDiscount.effectiveDiscount();
        }

        double final_price_after_discounts = cartDiscount.calculatePrice() - total_discount_amount;
        System.out.println("Final Price after all applicable discounts: $" + final_price_after_discounts);

    }

}
