package Builder;

public class MountainBeast implements BicycleBuilder{

    private Bicycle bicycle = new Bicycle();

    public void buildFrame(){
        bicycle.setFrame("Carbon Fiber Frame");
    }

    public void buildGears(){
        bicycle.setGears("12-Speed Gear");
    }

    public void buildTyres(){
        bicycle.setTires("Off-road Grip Tires");
    }
    
    public Bicycle getBicycle()
    {
        return bicycle;
    }

}
