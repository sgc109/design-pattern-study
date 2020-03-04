package decorator.coffee.component;

import decorator.coffee.Beverage;

public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "DarkRoast";
    }

    public double cost() {
        return .99;
    }
}
