package strategy.example2;

public class MallardDuck extends Duck implements Quackable, Flyable{
    public void display() {
        System.out.println("Display MallardDuck");
    }

    public void fly() {
        System.out.println("Fly");
    }

    public void quack() {
        System.out.println("Quack");
    }
}
