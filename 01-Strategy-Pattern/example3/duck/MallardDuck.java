package strategy.example3.duck;

import strategy.example3.flybehavior.FlyWithWings;
import strategy.example3.quackbehavior.Quack;

public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display() {
        System.out.println("Display MallardDuck");
    }
}
