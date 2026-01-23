package Builder;

public class Bicycle{
    private String frame;
    private String gears;
    private String tires;
    
    public void setFrame(String frame) {
        this.frame = frame;
    }
    public void setGears(String gears) {
        this.gears = gears;
    }
    public void setTires(String tires) {
        this.tires = tires;
    }
    @Override
    public String toString() {
        return "Bicycle [frame=" + frame + ", gears=" + gears + ", tires=" + tires + "]";
    }
}
