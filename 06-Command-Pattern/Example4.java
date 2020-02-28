/*
7개의 소켓을 가진 만능 리모컨
- 각 소켓마다 on/off 버튼이 있음
- global undo 버튼이 하나 있음
- 모드가 여러개 있는 디바이스 지원(e.g. 선풍기의 강풍/약풍/미풍/중지)
- 매크로 기능(버튼 한 번으로 여러 명령들을 한 번에 실행시키는 기능)
*/
interface Command {
    void execute();

    void undo();
}

class NoCommand implements Command {
    public void execute() {}

    public void undo() {}
}

class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName()
                    + "         " + offCommands[i].getClass().getName() + "\n");
        }
        stringBuff.append("[undo] " + undoCommand.getClass().getName());
        return stringBuff.toString();
    }
}

class Light {
    String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light on");
    }

    public void off() {
        System.out.println(location + " light off");
    }
}

class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}

class CeilingFan {
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;
    String location;
    int speed;

    public CeilingFan(String location) {
        this.location = location;
    }

    public void high() {
        System.out.println(location + " fan to high");
        speed = HIGH;
    }

    public void medium() {
        System.out.println(location + " fan to medium");
        speed = MEDIUM;
    }

    public void low() {
        System.out.println(location + " fan to low");
        speed = LOW;
    }

    public void off() {
        System.out.println(location + " fan off");
        speed = OFF;
    }

    public int getSpeed() {
        return speed;
    }
}

class CeilingFanOffCommand implements Command {
    CeilingFan ceilingFan;
    int prevSpeed = CeilingFan.OFF;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
    }

    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}

class CeilingFanHighCommand implements Command {
    CeilingFan ceilingFan;
    int prevSpeed = CeilingFan.OFF;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
    }

    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}

class MacroCommand implements Command {
    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

    public void undo() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].undo();
        }
    }
}

public class Example4 {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        CeilingFan ceilingFan = new CeilingFan("Living Room");
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);
        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);

        Light livingRoomLight = new Light("Living Room");
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        Command[] comeBackHome = {ceilingFanHigh, livingRoomLightOn};
        Command[] goOutOfHome = {ceilingFanOff, livingRoomLightOff};

        MacroCommand comeBackHomeMacro = new MacroCommand(comeBackHome);
        MacroCommand goOutOfHomeMacro = new MacroCommand(goOutOfHome);

        remoteControl.setCommand(0, comeBackHomeMacro, goOutOfHomeMacro);

        remoteControl.onButtonWasPushed(0);

        System.out.println(remoteControl);

        remoteControl.offButtonWasPushed(0);

        remoteControl.undoButtonWasPushed();
    }
}
