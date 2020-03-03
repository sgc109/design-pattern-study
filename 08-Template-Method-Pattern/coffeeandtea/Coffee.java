package templatemethod.coffeeandtea;

public class Coffee extends CaffeineBeverageWithHook {
    void brew() {
        System.out.println("Dripping Coffee through filter!");
    }

    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
