package Builder;

public class Commuter implements BicycleBuilder {
    private Bicycle bicycle = new Bicycle();

    public void buildFrame(){
        bicycle.setFrame("Aluminium Frame");
    }

    public void buildGears(){
        bicycle.setGears("Single Speed Gear");
    }

    public void buildTyres(){
        bicycle.setTires("Road Tires");
    }

    public Bicycle getBicycle()
    {
        return bicycle;
    }
}
