class Dough {}
class Sauce {}
class Cheese {}
class Veggies {}
class Pepperoni {}
class Clams {}

interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();
}

class ThinCrustDough extends Dough {}
class MarinaraSauce extends Sauce {}
class ReggianoCheese extends Cheese {}
class SlicedPepperoni extends Pepperoni {}
class FreshClams extends Clams {}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    public Dough createDough() {
        return new ThinCrustDough();
    }

    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    public Veggies[] createVeggies() {
        Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
        return veggies;
    }

    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    public Clams createClam() {
        return new FreshClams();
    }
}

abstract class Pizza {
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    abstract void prepare();
    void bake() {}
    void cut() {}
    void box() {}
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        veggies = ingredientFactory.createVeggies();
        cheese = ingredientFactory.createCheese();
        pepperoni = ingredientFactory.createPepperoni();
        clams = ingredientFactory.createClam();
    }
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

class NYPizzaStore extends PizzaStore {

    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (item.equals("cheese")) {
           pizza = new CheesePizza(ingredientFactory);
        } else if (item.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
        } else if (item.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
        } else if (item.equals("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
        }
        return pizza;
    }
}
