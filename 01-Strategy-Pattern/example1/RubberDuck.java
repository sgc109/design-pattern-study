package strategy.example1;

public class RubberDuck extends Duck {
    public void quack() {
        System.out.println("Squeak");
    }

    public void fly() {
        // override to do nothing
    }

    public void display() {
        System.out.println("Display RedheadDuck");
    }
}
