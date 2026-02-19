public class Course extends AbstractComposite
{
    public Course(String name) 
    {
        super(name);
    }

    public void addLesson(CourseComponent component) 
    {
        if (component instanceof Lesson) 
        {
            addComponent(component);
        } 
        else 
        {
            throw new IllegalArgumentException("Only Lesson components can be added to a Course.");
        }
    }

    public void removeLesson(CourseComponent component) 
    {
        removeComponent(component);
    }

    // List<CourseComponent> getLessons() 
    // {
    //     return getComponents();
    // }

    @Override
    public void displayDetails() 
    {
        System.out.println("Course: " + name);
        for (CourseComponent component : components) 
        {
            component.displayDetails();
        }
        System.out.println("Total Course Duration: " + calculateDuration() + " hours");
        System.out.println("Total Course Price: $" + calculatePrice());
        System.out.println("-----------------------------");
    }

}
