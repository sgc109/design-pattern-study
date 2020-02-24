class Pizza {
    void prepare() {}
    void bake() {}
    void cut() {}
    void box() {}
}

abstract class PizzaStore {
    Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}

class NYStyleCheesePizza extends Pizza {}
class NYStyleGreekPizza extends Pizza {}
class NYStylePepperoniPizza extends Pizza {}

class NYPizzaStore extends PizzaStore {
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (item.equals("greek")) {
            return new NYStyleGreekPizza();
        } else if (item.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else return null;
    }
}

class ChicagoStyleCheesePizza extends Pizza {}
class ChicagoStyleGreekPizza extends Pizza {}
class ChicagoStylePepperoniPizza extends Pizza {}

class ChicagoPizzaStore extends PizzaStore {
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if (item.equals("greek")) {
            return new ChicagoStyleGreekPizza();
        } else if (item.equals("pepperoni")) {
            return new ChicagoStylePepperoniPizza();
        } else return null;
    }
}
