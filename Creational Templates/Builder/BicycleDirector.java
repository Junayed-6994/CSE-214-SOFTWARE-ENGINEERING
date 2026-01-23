package Builder;

public class BicycleDirector {
    private BicycleBuilder builder;

    public void setBuilder(BicycleBuilder builder) {
        this.builder = builder;
    }

    public Bicycle construct(){
        builder.buildFrame();
        builder.buildGears();
        builder.buildTyres();
        return builder.getBicycle();
    }
}
