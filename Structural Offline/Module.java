import java.util.ArrayList;
import java.util.List;

public class Module implements CourseComponent
{
    private final String name;
    private final List<Course> courses;

    public Module(String name) 
    {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) 
    {
        courses.add(course);
    }

    public void removeCourse(Course course) 
    {
        courses.remove(course);
    }

    public List<Course> getCourses() 
    {
        return new ArrayList<>(courses);
    }

    @Override
    public double calculatePrice() 
    {
        return courses.stream().mapToDouble(Course::calculatePrice).sum();
    }

    @Override
    public double calculateDuration() 
    {
        return courses.stream().mapToDouble(Course::calculateDuration).sum();
    }

    @Override
    public String getName() 
    {
        return name;
    }

    @Override
    public void displayDetails() 
    {
        System.out.println("Module: " + name);
        for (Course course : courses) 
        {
            course.displayDetails();
        }
        System.out.println("Total Duration: " + calculateDuration() + " hours");
        System.out.println("Total Price: $" + calculatePrice());
    }

    
}
