/*
7개의 소켓을 가진 만능 리모컨
- 각 소켓마다 on/off 버튼이 있음
- global undo 버튼이 하나 있음
- undo 히스토리 관리하여 여러번 실행취소가 가능
- redo 히스토리 관리하여 여러번 다시실행 가능
*/
interface Command {
    void execute();

    void undo();

    void redo();
}

class NoCommand implements Command {
    public void execute() {}

    public void undo() {}

    public void redo() {}
}

class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Deque<Command> undoStack = new LinkedList<>();
    Deque<Command> redoStack = new LinkedList<>();

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoStack.offerLast(onCommands[slot]);
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoStack.offerLast(offCommands[slot]);
    }

    public void undoButtonWasPushed() {
        if (!undoStack.isEmpty()) {
            Command prevCommand = undoStack.pollLast();
            redoStack.offerLast(prevCommand);
            prevCommand.undo();
        }
    }

    public void redoButtonWasPushed() {
        if (!redoStack.isEmpty()) {
            Command nextCommand = redoStack.pollLast();
            undoStack.offerLast(nextCommand);
            nextCommand.redo();
        }
    }

    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName()
                    + "         " + offCommands[i].getClass().getName() + "\n");
        }
        stringBuff.append("[undo] " + undoStack.getClass().getName()); // added
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

    public void redo() {
        light.on();
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

    public void redo() {
        light.off();
    }
}

public class Example6 {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.undoButtonWasPushed();
        remoteControl.undoButtonWasPushed();
        remoteControl.redoButtonWasPushed();
        remoteControl.redoButtonWasPushed();
    }
}
