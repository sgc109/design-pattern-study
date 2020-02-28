package strategy.example2;

public class RedheadDuck extends Duck implements Quackable, Flyable{
    public void display() {
        System.out.println("Display RedheadDuck");
    }

    public void fly() {
        System.out.println("Fly");
    }

    public void quack() {
        System.out.println("Quack");
    }
}
