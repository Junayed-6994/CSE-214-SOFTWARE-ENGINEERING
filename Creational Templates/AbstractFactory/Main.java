package AbstractFactory;

import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

//AP 1
interface Processor{
    String getDesc();
}

//CP 1
class IntelXeonProcessor implements Processor{
    public String getDesc() {
        return "Intel Xeon Processor";
    }
}

//CP 2
class ARMProcessor implements Processor{
    public String getDesc() {
        return "ARM Processor";
    }
}

//AP 2
interface Display{
    String getDesc();
}

//CP 3
class IPSDisplay implements Display {
    public String getDesc() {
        return "IPS Display";
    }
}

//CP 4
class OLEDDisplay implements Display {
    public String getDesc() {
        return "OLED Display";
    }
}

//AF
interface ComputerFactory{
    Processor createProcessor();
    Display createDisplay();
}

//CF 1
class WorkProFactory implements ComputerFactory {
    public Processor createProcessor() {
        return new IntelXeonProcessor();
    }
    
    public Display createDisplay() {
        return new IPSDisplay();
    }
}
 //CF 2
class LiteMaxFactory implements ComputerFactory {
    public Processor createProcessor() {
        return new ARMProcessor();
    }
    
    public Display createDisplay() {
        return new OLEDDisplay();
    }
}

class Computer{
    private String model;
    private Processor processor;
    private Display display;

    public Computer(String model, ComputerFactory factory) {
        this.model = model;
        this.processor = factory.createProcessor();
        this.display = factory.createDisplay();
    }

    public void showDescription() {
        System.out.println("\n========== Computer Specification ==========");
        System.out.println("Model: " + model);
        System.out.println("Processor: " + processor.getDesc());
        System.out.println("Display: " + display.getDesc());
        System.out.println("============================================\n");
    }

}

public class Main {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select your computer model:");
        System.out.println("1. WorkPro (Professional)");
        System.out.println("2. LiteMax (Lightweight)");
        System.out.print("Enter choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        
        ComputerFactory factory;
        String model;
        
        if (choice == 1) {
            factory = new WorkProFactory();
            model = "WorkPro";
        } else {
            factory = new LiteMaxFactory();
            model = "LiteMax";
        }
        
        Computer computer = new Computer(model, factory);
        computer.showDescription();
        
        scanner.close();
    }
}
