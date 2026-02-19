import java.util.ArrayList;
import java.util.List;

public class Course implements CourseComponent
{
    private final String name;
    private final List<Lesson> lessons;
    
    public Course(String name) 
    {
        this.name = name;
        this.lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) 
    {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) 
    {
        lessons.remove(lesson);
    }

    public List<Lesson> getLessons() 
    {
        return new ArrayList<>(lessons);
    }

    @Override
    public double calculatePrice() 
    {
        return lessons.stream().mapToDouble(Lesson::calculatePrice).sum();
    }

    @Override
    public double calculateDuration() 
    {
        return lessons.stream().mapToDouble(Lesson::calculateDuration).sum();
    }

    @Override
    public String getName() 
    {
        return name;
    }

    @Override
    public void displayDetails() 
    {
        System.out.println("Course: " + name);
        for (Lesson lesson : lessons) 
        {
            lesson.displayDetails();
        }
        System.out.println("Total Duration: " + calculateDuration() + " hours");
        System.out.println("Total Price: $" + calculatePrice());
    }

}
