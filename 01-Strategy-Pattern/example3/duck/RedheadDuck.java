package strategy.example3.duck;

import strategy.example3.flybehavior.FlyWithWings;
import strategy.example3.quackbehavior.Quack;

public class RedheadDuck extends Duck {
    public RedheadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display() {
        System.out.println("Display RedheadDuck");
    }
}
