package Command;

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Receiver 1 - Light
class Light {
    private String location;
    
    public Light(String location) {
        this.location = location;
    }
    
    public void on() {
        System.out.println("💡 " + location + " Light is ON");
    }
    
    public void off() {
        System.out.println("🌑 " + location + " Light is OFF");
    }
}

// Receiver 2 - Fan
class Fan {
    private String location;
    
    public Fan(String location) {
        this.location = location;
    }
    
    public void on() {
        System.out.println("🌀 " + location + " Fan is ON");
    }
    
    public void off() {
        System.out.println("⭕ " + location + " Fan is OFF");
    }
}

// Receiver 3 - TV
class TV {
    private String location;
    
    public TV(String location) {
        this.location = location;
    }
    
    public void on() {
        System.out.println("📺 " + location + " TV is ON");
    }
    
    public void off() {
        System.out.println("📴 " + location + " TV is OFF");
    }
}

// Concrete Command 1 - Light ON
class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.on();
    }
    
    @Override
    public void undo() {
        light.off();
    }
}

// Concrete Command 2 - Light OFF
class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.off();
    }
    
    @Override
    public void undo() {
        light.on();
    }
}

// Concrete Command 3 - Fan ON
class FanOnCommand implements Command {
    private Fan fan;
    
    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }
    
    @Override
    public void execute() {
        fan.on();
    }
    
    @Override
    public void undo() {
        fan.off();
    }
}

// Concrete Command 4 - Fan OFF
class FanOffCommand implements Command {
    private Fan fan;
    
    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }
    
    @Override
    public void execute() {
        fan.off();
    }
    
    @Override
    public void undo() {
        fan.on();
    }
}

// Concrete Command 5 - TV ON
class TVOnCommand implements Command {
    private TV tv;
    
    public TVOnCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        tv.on();
    }
    
    @Override
    public void undo() {
        tv.off();
    }
}

// Concrete Command 6 - TV OFF
class TVOffCommand implements Command {
    private TV tv;
    
    public TVOffCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        tv.off();
    }
    
    @Override
    public void undo() {
        tv.on();
    }
}

// Invoker - Remote Control
class RemoteControl {
    private Command command;
    private Command lastCommand;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
        lastCommand = command; // Store for undo
    }
    
    public void pressUndo() {
        if (lastCommand != null) {
            System.out.println("⏪ Undoing last action...");
            lastCommand.undo();
        } else {
            System.out.println("⚠️  No action to undo");
        }
    }
}

// Main Class
public class SmartHomeSystem {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    SMART HOME REMOTE CONTROL          ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Create receivers (devices)
        Light livingRoomLight = new Light("Living Room");
        Fan bedroomFan = new Fan("Bedroom");
        TV kitchenTV = new TV("Kitchen");
        
        // Create commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command fanOn = new FanOnCommand(bedroomFan);
        Command fanOff = new FanOffCommand(bedroomFan);
        Command tvOn = new TVOnCommand(kitchenTV);
        Command tvOff = new TVOffCommand(kitchenTV);
        
        // Create invoker (remote)
        RemoteControl remote = new RemoteControl();
        
        // Scenario 1: Control light
        System.out.println("--- Controlling Living Room Light ---");
        remote.setCommand(lightOn);
        remote.pressButton();
        System.out.println();
        
        remote.setCommand(lightOff);
        remote.pressButton();
        System.out.println();
        
        // Undo last action (turn light back on)
        remote.pressUndo();
        System.out.println();
        
        // Scenario 2: Control fan
        System.out.println("--- Controlling Bedroom Fan ---");
        remote.setCommand(fanOn);
        remote.pressButton();
        System.out.println();
        
        // Scenario 3: Control TV
        System.out.println("--- Controlling Kitchen TV ---");
        remote.setCommand(tvOn);
        remote.pressButton();
        System.out.println();
        
        remote.setCommand(tvOff);
        remote.pressButton();
        System.out.println();
        
        // Undo TV off (turn TV back on)
        remote.pressUndo();
        System.out.println();
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       END OF DEMONSTRATION            ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}