## 08 Template Method Pattern

![image](https://user-images.githubusercontent.com/7943694/75777402-465b7600-5d99-11ea-8ef7-406e62dc1e77.png)

**superclass 가 알고리즘을 정의하고, 단계별로 실행될 메소드 등 로직을 정해놓으면 subclass 에게 일부 로직의 구현만을 맡기는 패턴**
* 프레임워크에서 많이 사용된다. 
* 프레임워크는 개발자에게 일부 로직의 구현만 위임하고, 그 로직들이 언제 어떤 로직이 실행될지에 대한 흐름은 직접 제어하기 때문이다. (e.g. Android 의 Activity lifecycle callback)
* factory method 는 template method 의 특수한 형태다
* 예시
```java
public abstract class CaffeineBeverage {
    final void prepareRecipe() { // Template Method
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()) {
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

```

### Template Method
* 알고리즘이 정의되어있는 메소드다
* Template Method 의 일부 Step 은 아직 정의되지 않아 subclass 가 무조건 구현해야만 한다.
* final 로 선언하여 알고리즘이 변경되지 않도록 한다

### Concrete Method
* Template Method 를 포함하는 클래스 내 이미 구현되어있는 메소드
* Subclass 에서 이를 호출하기도한다
* final 로 선언한다

### Hook
* Optional 한 step 을 정의 할 수 있다
* Subclass 가 꼭 구현하지 않아도된다
* 보통 아무 로직이 없거나 default 로직이 있다.
* boolean 을 반환하는 hook 을 만들어 template method 내의 if문에서 호출하면 조건부 실행제어가 가능하다
* 특정 Step 의 실행 직전/후에 subclass 가 react 할 수 있는 통로로 사용될 수 있다
* 사용 예
    - JFrame 의 paint
    - Applet 의 paint

일부 디테일만 다른 여러 비슷한 로직들을 추상화시켜 공통된부분만 분리시킨 뒤 캡슐화하여 template method 로 만들고 디테일은 subclass 에게 위임하면 중복 코드를 많이 줄일 수 있어서, 로직의 변경도 한 곳에서 관리할수있다.

### 항상 상속의 형태인것은 아니다 
* Arrays.sort 는 설계자가 모든 클래스에 대해서 사용할 수 있게 하기 위해 static method 로 정의하였고, 자바의 배열은 상속이 불가능하다는 점으로인해 interface 를 사용했다
* 정렬되는 item 에게 비교 로직의 구현을 맡긴다 
* Comparable 의 item 이 compareTo 만 구현하면 이를 호출하여 사용하는것은 sort 내에서 호출하는 mergeSort 내에서 한다. 
```java
    public static void sort(Object[] a) {
        Object aux[] = (Object[]) a.clone();
        mergeSort(aux, a, 0, a.length, 0);
    }

    private static void mergeSort(Object src[], Object dest[], int low, int high, int off) {
        for (int i = low; i < high; i++) {
            for (int j = i; j > low &&
                    ((Comparable) dest[j - 1]).compareTo((Comparable) dest[j]) > 0; j--) {
                swap(dest, j, j - 1);
            }
        }
        return;
    }
```

### Hollywood Principle
* *Don't call us, we'll call you*
* Template Method Pattern 이 사용하는 설계 원칙이다
* high-level 컴포넌트가 low-level 컴포넌트의 로직의 호출 타이밍을 정하고, low-level 컴포넌트는 단지 로직을 정의하기만 하는 원칙이다.

### 실생활 예시
* JFrame
```java
public class MyFrame extends JFrame {
    public MyFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
    }

    public void paint(Graphics graphics) { // Hook
        super.paint(graphics);
        String msg = “I rule !!”;
        graphics.drawString(msg, 100, 100);
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame(“Head First Design Patterns”);
    }
}
```

* Applets
```java
public class MyApplet extends Applet {
    String message;

    public void init() {
        message = "Hello World, I’m alive !";
        repaint(); // Concrete Method of Applet class
    }

    public void start() {
        message = "Now I’m starting up...";
        repaint();
    }

    public void stop() {
        message = "Oh, now I’m being stopped...";
        repaint();
    }

    public void destroy() {
        // applet is going away...
    }

    public void paint(Graphics g) { // Hook
        g.drawString(message, 5, 15);
    }
}
```
