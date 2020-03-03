package templatemethod.coffeeandtea;

public class Test {
    public static void main(String[] args) {
        CaffeineBeverageWithHook coffee = new CoffeeWithHook();
        coffee.prepareRecipe();
    }
}
