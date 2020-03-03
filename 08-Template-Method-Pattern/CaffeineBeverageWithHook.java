package templatemethod.coffeeandtea;

public abstract class CaffeineBeverageWithHook {
    final void prepareRecipe() { // Template Method
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() { // Concrete Method
        System.out.println("Boil water");
    }

    void pourInCup() { // Concrete Method
        System.out.println("Pour in cup");
    }

    boolean customerWantsCondiments() { // Hook
        return true;
    }
}
