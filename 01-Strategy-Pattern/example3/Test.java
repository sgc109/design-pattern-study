package strategy.example3;

import strategy.example3.duck.DecoyDuck;
import strategy.example3.duck.Duck;
import strategy.example3.flybehavior.FlyWithWings;
import strategy.example3.quackbehavior.Quack;

public class Test {
    public static void main(String[] args) {
        Duck decoyDuck = new DecoyDuck();

        decoyDuck.display();
        decoyDuck.performQuack();
        decoyDuck.performFly();

        decoyDuck.setFlyBehavior(new FlyWithWings());
        decoyDuck.setQuackBehavior(new Quack());

        decoyDuck.performQuack();
        decoyDuck.performFly();
    }
}
