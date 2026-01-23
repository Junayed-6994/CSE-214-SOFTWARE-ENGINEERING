package Builder;

public class Main { 
    public static void main(String[] args){
        BicycleDirector director = new BicycleDirector();

        //Building a commuter
        BicycleBuilder commuterBuilder = new Commuter();
        director.setBuilder(commuterBuilder);
        Bicycle commuter = director.construct();
        System.out.println(commuter);

        //Building a MountainBeast
        BicycleBuilder mountainBeast = new MountainBeast();
        director.setBuilder(mountainBeast);
        Bicycle mountain = director.construct();
        System.out.println(mountain);

    }
}
