package strategy.example3.duck;

import strategy.example3.flybehavior.FlyNoWay;
import strategy.example3.quackbehavior.MuteQuack;

public class DecoyDuck extends Duck {
    public DecoyDuck() {
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }

    public void display() {
        System.out.println("Display DecoyDuck");
    }
}
