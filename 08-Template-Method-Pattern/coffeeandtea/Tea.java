package templatemethod.coffeeandtea;

public class Tea extends CaffeineBeverageWithHook {
    void brew() {
        System.out.println("Steep tea");
    }

    void addCondiments() {
        System.out.println("Add lemon");
    }
}
