public class Lesson implements CourseComponent
{
    private final String name;
    private final double duration;
    private final double price;

    public Lesson(String name, double duration, double price) 
    {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    @Override
    public double calculatePrice() 
    {
        return price;
    }

    @Override
    public double calculateDuration() 
    {
        return duration;
    }

    @Override
    public String getName() 
    {
        return name;
    }

    @Override
    public void displayDetails() 
    {
        System.out.println( "Lesson: " + name + ", Duration: " + duration + " hours, Price: $" + price);
    }
}
