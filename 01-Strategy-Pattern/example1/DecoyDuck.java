package strategy.example1;

public class DecoyDuck extends Duck {
    public void quack() {
        // override to do nothing
    }

    public void fly() {
        // override to do nothing
    }

    public void display() {
        System.out.println("Display DecoyDuck");
    }
}
