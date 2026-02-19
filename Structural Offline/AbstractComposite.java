import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComposite implements CourseComponent
{
    protected final String name;
    protected final List<CourseComponent> components;

    public AbstractComposite(String name)
    {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponent(CourseComponent component)
    {
        components.add(component);
    }

    public void removeComponent(CourseComponent component)
    {
        components.remove(component);
    }

    // public List<CourseComponent> getComponents()
    // {
    //     return new ArrayList<>(components);
    // }

    @Override
    public double calculatePrice()
    {
        return components.stream().mapToDouble(CourseComponent::calculatePrice).sum();
    }

    @Override
    public double calculateDuration()
    {
        return components.stream().mapToDouble(CourseComponent::calculateDuration).sum();
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public abstract void displayDetails();
}
