package strategy.example3.duck;

import strategy.example3.flybehavior.FlyNoWay;
import strategy.example3.quackbehavior.Squeak;

public class RubberDuck extends Duck {
    public RubberDuck() {
        quackBehavior = new Squeak();
        flyBehavior = new FlyNoWay();
    }

    public void display() {
        System.out.println("Display RedheadDuck");
    }
}
