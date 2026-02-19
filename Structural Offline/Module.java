

public class Module extends AbstractComposite implements ModuleComponent
{
    public Module(String name) 
    {
        super(name);
    }

    public void addCourse(CourseComponent course) 
    {
        if(course instanceof Course) 
        {
            addComponent(course);
        } 
        else 
        {
            throw new IllegalArgumentException("Only Course components can be added to a Module.");
        }
    }

    public void removeCourse(CourseComponent course) 
    {
        removeComponent(course);
    }

    // public List<CourseComponent> getCourses() 
    // {
    //     return getComponents();
    // }

    @Override
    public void displayDetails() 
    {
        System.out.println("Module: " + name);
        System.out.println("------------------------------");
        for (CourseComponent component : components) 
        {
            component.displayDetails();
        }
        System.out.println("Total Module Duration: " + calculateDuration() + " hours");
        System.out.println("Total Module Price: $" + calculatePrice());
        System.out.println("-----------------------------");
    }
}
